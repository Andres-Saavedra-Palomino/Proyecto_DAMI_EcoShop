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

  var p1 = Producto("0","producto 0")
  var p2 = Producto("1","producto 1")
  var p3 = Producto("2","producto 2")
  var p4 = Producto("3","producto 3")
  var p5 = Producto("4","producto 4")
  var p6 = Producto("5","producto 5")
  var p7 = Producto("6","producto 6")
  var p8 = Producto("7","producto 7")
  var p9 = Producto("8","producto 8")
  var p10 = Producto("9","producto 9")
  var temp = listOf<Producto>(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10)

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
    binding.homeRvPlayas.adapter = ProductoAdapter(temp)

    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}