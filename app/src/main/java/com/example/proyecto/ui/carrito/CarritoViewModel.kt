package com.example.proyecto.ui.carrito

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarritoViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "Carrito works!"
  }
  val text: LiveData<String> = _text

  private val _lista= MutableLiveData<List<Carrito>>().apply {
    value= listOf(Carrito("","R","p","3","3"),Carrito("","P",
    "q","4","5"))
  }
  val lista:LiveData<List<Carrito>> = _lista
}