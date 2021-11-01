package com.example.proyecto.ui.home

import android.widget.Adapter
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class HomeViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is home Fragment"
  }

  private val _descripcion_1 = MutableLiveData<String>().apply {
    value = "Productos en Oferta!"
  }
  val text: LiveData<String> = _text
  val descripcion: LiveData<String> = _descripcion_1

}