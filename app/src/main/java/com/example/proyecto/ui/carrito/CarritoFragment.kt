package com.example.proyecto.ui.carrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.databinding.FragmentCarritoBinding

class CarritoFragment : Fragment() {

  private lateinit var carritoViewModel: CarritoViewModel
  private var _binding: FragmentCarritoBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    carritoViewModel =
      ViewModelProvider(this).get(CarritoViewModel::class.java)

    _binding = FragmentCarritoBinding.inflate(inflater, container, false)
    val root: View = binding.root

    carritoViewModel.text.observe(viewLifecycleOwner, Observer {
      binding.text.text = it
    })
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}