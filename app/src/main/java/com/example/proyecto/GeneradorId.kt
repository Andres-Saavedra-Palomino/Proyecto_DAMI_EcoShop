package com.example.proyecto

import kotlin.random.Random

class GeneradorId {
  fun generarId(digitos: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..digitos)
      .map { allowedChars.random() }
      .joinToString("")
  }
}