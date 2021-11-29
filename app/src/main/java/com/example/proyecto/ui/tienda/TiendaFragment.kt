package com.example.proyecto.ui.tienda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentTiendaBinding
import com.example.proyecto.model.ProductoAdapter
import com.squareup.picasso.Picasso

class TiendaFragment : Fragment() {

  private lateinit var homeViewModel: TiendaViewModel
  private var _binding: FragmentTiendaBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
      ViewModelProvider(this).get(TiendaViewModel::class.java)

    _binding = FragmentTiendaBinding.inflate(inflater, container, false)
    val root: View = binding.root

    Picasso.get()
      .load("https://firebasestorage.googleapis.com/v0/b/navagioecoshop.appspot.com/o/NavagioPlaya.jpg?alt=media&token=ed8668ab-9739-46bf-b47f-3eb45c80eb1f")
      .error(R.drawable.ic_baseline_error_outline_24)
      .into(binding.NavagioPlaya)

    homeViewModel.text.observe(viewLifecycleOwner, Observer {
      binding.textHome.text = it
    })

    homeViewModel.descripcion.observe(viewLifecycleOwner, Observer {
      binding.listaDescripcion.text = it
    })
    homeViewModel.lista.observe(viewLifecycleOwner,Observer{
      binding.homeRvPlayas.adapter = ProductoAdapter(it)
    })

    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}