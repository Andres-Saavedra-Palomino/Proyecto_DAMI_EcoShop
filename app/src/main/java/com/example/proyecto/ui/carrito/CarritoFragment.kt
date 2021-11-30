package com.example.proyecto.ui.carrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentCarritoBinding
import com.squareup.picasso.Picasso

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

    var lista= listOf(Carrito("","R","p","3","3"),Carrito("","P",
      "q","4","5"))

    binding.rCarrito.adapter=CarritoAdapter(lista, object: Quitar {
      override fun onClick(posicion: Int,v: View) {
        lista.drop(posicion)
      }
    })
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}