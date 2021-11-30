package com.example.proyecto.ui.buscar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentBuscarBinding
import com.example.proyecto.model.Catalogo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BuscarFragment : Fragment() {

  private lateinit var notificationsViewModel: BuscarViewModel
  val db = Firebase.firestore;
  var TAG:String="PlayaFragment"
  var arProfiles= arrayListOf<Catalogo>()

  lateinit var rvCatalogo: RecyclerView

  private var _binding: FragmentBuscarBinding? = null

  private val binding get() = _binding!!
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    notificationsViewModel =
      ViewModelProvider(this).get(BuscarViewModel::class.java)

    _binding = FragmentBuscarBinding.inflate(inflater, container, false)
    val root: View = binding.root

    notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
      binding.text.text = it
    })
    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    rvCatalogo=view.findViewById(R.id.rvCatalogo)
    rvCatalogo.layoutManager= LinearLayoutManager(requireContext())

    obtenerCatalogo()
  }

  fun obtenerCatalogo(){
    db.collection("/Producto")

      .get()
      .addOnSuccessListener { documents ->
        arProfiles.addAll(documents.toObjects(Catalogo::class.java))
        rvCatalogo.adapter= CatalogoListAdapter(arProfiles,requireContext())
      }
      .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
      }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}