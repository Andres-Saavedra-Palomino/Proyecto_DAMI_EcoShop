package com.example.proyecto.model

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyecto.GeneradorId
import com.example.proyecto.databinding.ActivityProductoBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.streams.asSequence

class ProductoActivity : AppCompatActivity() {

  private lateinit var binding: ActivityProductoBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityProductoBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnProductoRegistrar.setOnClickListener {
      if (!esValido()) {

      } else
        guardarProducto()
    }
//    binding.btnProductoListar.setOnClickListener {
//      binding.lvProductoListado.adapter = ProductoAdapter(listarProductos())
//    }
  }


//  private fun listarProductos(): List<Producto> {
//    val db = Firebase.firestore
//    val temp = mutableListOf<Producto>()
//    db.collection("Producto").get()
//      .addOnSuccessListener { result ->
//        for (document in result) {
//          temp.add(Producto(document.id, document.data.getValue("descripcion").toString()))
//        }
//      }
//      .addOnFailureListener { exception ->
//        Log.w(TAG, "Error getting documents.", exception)
//      }
//    return temp
//  }

  private fun esValido(): Boolean {

    return true
  }

  private fun guardarProducto() {
    val db = Firebase.firestore
    var codigo = GeneradorId().generarId(9)
    val prod = hashMapOf(
      "descripcion" to binding.etProductoNombre.text.toString(),
      "precio" to binding.etProductoPrecio.text.toString(),
      "descuento" to binding.etProductoDescuento.text.toString(),
      "stock" to binding.etProductoStock.text.toString(),
      "imagen" to binding.etProductoImagen.text.toString(),
      "categoria" to binding.etProductoCategoria.text.toString()
    )
    db.collection("Producto").add(prod)
      .addOnSuccessListener { documentReference ->
        Log.d(TAG, "Documento agregado. ID: ${documentReference.id}")
      }
      .addOnFailureListener { e ->
        Log.w(TAG, "Error al agregar documento", e)
      }
    //db.collection("nuevo").document().set(prod)
  }
}
