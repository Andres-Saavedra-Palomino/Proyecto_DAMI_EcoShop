package com.example.proyecto.ui.tienda

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentTiendaBinding

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