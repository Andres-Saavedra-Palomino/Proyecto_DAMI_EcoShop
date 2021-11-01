package com.example.proyecto.ui.buscar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentBuscarBinding

class BuscarFragment : Fragment() {

  private lateinit var buscarviewModel: BuscarViewModel
  private var _binding: FragmentBuscarBinding? = null

  private val binding get() = _binding!!
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    buscarviewModel = ViewModelProvider(this).get(BuscarViewModel::class.java)

    _binding = FragmentBuscarBinding.inflate(inflater, container, false)
    val root: View = binding.root

    binding.etBuscarNombreProducto.setText("")
    binding.btnBuscarPorNombre.setOnClickListener {
      buscarProductoPorNombre()
    }

    return root
  }

  private fun buscarProductoPorNombre() {
    Toast.makeText(requireContext(), "buscando...", Toast.LENGTH_SHORT).show()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}