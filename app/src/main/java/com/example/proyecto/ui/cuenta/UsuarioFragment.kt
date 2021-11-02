package com.example.proyecto.ui.cuenta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentUsuarioBinding

enum class ProviderType {
  BASIC,
  GOOGLE
}

class UsuarioFragment : Fragment() {

  private lateinit var usuarioviewModel: UsuarioViewModel
  private var _binding: FragmentUsuarioBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    usuarioviewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
    _binding = FragmentUsuarioBinding.inflate(inflater, container, false)

    val root: View = binding.root

    usuarioviewModel.text.observe(viewLifecycleOwner,Observer{
    })

    return root
  }


}