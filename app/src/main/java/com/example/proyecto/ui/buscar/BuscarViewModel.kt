package com.example.proyecto.ui.buscar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BuscarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Catalogo"
    }
    val text: LiveData<String> = _text
}