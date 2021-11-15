package com.example.proyecto.ui.cuenta

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto.MainActivity
import com.example.proyecto.R
import com.example.proyecto.databinding.ActivityAuthBinding
import com.example.proyecto.databinding.ActivityRegUsuarioBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class RegUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegUsuarioBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        title = "Crear Cuenta"

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)

        val email: String = prefs.getString("email", "").toString()

        binding.guardarBtn.setOnClickListener {
            db.collection("Usuario").document(email).set(
                hashMapOf("documento" to binding.nroDocumentoET.text.toString(),
                "nombre" to binding.nombreET.text.toString(),
                "apellido" to binding.apellidoET.text.toString(),
                "telefono" to binding.telefonoET.text.toString(),
                "fecha_regis" to Calendar.getInstance().time
                )
            )
            verMain()
        }
    }

    private fun verMain(){
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }
}