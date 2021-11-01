package com.example.proyecto.ui.carrito

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarritoViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "Carrito works!"
  }
  val text: LiveData<String> = _text
}