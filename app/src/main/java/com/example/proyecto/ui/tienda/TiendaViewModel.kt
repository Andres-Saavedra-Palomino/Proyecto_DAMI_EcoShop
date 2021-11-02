package com.example.proyecto.ui.tienda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TiendaViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is home Fragment"
  }

  private val _descripcion_1 = MutableLiveData<String>().apply {
    value = "Productos en Oferta!"
  }
  var p1 = Producto("0","producto 0")
  var p2 = Producto("1","producto 1")
  var p3 = Producto("2","producto 2")
  var p4 = Producto("3","producto 3")
  var p5 = Producto("4","producto 4")
  var temp = listOf<Producto>(p1,p2,p3,p4,p5)

  private val _lista = MutableLiveData<List<Producto>>().apply {
    value = temp
  }

  val lista:LiveData<List<Producto>> = _lista
  val text: LiveData<String> = _text
  val descripcion: LiveData<String> = _descripcion_1

}