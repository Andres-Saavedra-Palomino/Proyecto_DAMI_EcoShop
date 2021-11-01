package com.example.proyecto.ui.playa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayaViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "Playa works!"
  }
  val text: LiveData<String> = _text
}