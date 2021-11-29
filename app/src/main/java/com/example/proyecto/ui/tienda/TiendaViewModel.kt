package com.example.proyecto.ui.tienda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto.model.Producto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TiendaViewModel : ViewModel() {

  val db = Firebase.firestore;

  private val _text = MutableLiveData<String>().apply {
    value = "Navagio's Eco Shop"
  }

  private val _descripcion_1 = MutableLiveData<String>().apply {
    value = "Productos en Oferta!"
  }

  private val _lista = MutableLiveData<List<Producto>>().apply {
    var temp = arrayListOf<Producto>()
    db.collection("/Producto")
      .get()
      .addOnSuccessListener { documents ->
        temp.addAll(documents.toObjects(Producto::class.java))
        value = temp.filter { p -> p.descuento.toInt() > 0 }
      }
      .addOnFailureListener { exception ->
        Log.w("TiendaFragment", "Error getting documents: ", exception)
      }
  }

  val lista: LiveData<List<Producto>> = _lista
  val text: LiveData<String> = _text
  val descripcion: LiveData<String> = _descripcion_1

}