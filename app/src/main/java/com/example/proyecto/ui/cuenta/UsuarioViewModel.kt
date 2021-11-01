package com.example.proyecto.ui.cuenta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsuarioViewModel : ViewModel() {
  private val _text = MutableLiveData<String>().apply {
    value = "Usuario works!"
  }
val text:LiveData<String> = _text
}