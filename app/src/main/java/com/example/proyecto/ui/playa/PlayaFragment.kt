package com.example.proyecto.ui.playa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.databinding.FragmentPlayaBinding

class PlayaFragment : Fragment() {

  private lateinit var notificationsViewModel: PlayaViewModel
  private var _binding: FragmentPlayaBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    notificationsViewModel =
      ViewModelProvider(this).get(PlayaViewModel::class.java)

    _binding = FragmentPlayaBinding.inflate(inflater, container, false)
    val root: View = binding.root

    notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
      binding.text.text = it
    })
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}