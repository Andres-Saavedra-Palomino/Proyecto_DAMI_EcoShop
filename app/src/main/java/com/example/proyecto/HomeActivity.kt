package com.example.proyecto

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")*/


        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)

        val email: String = prefs.getString("email", "Email no encontrado").toString()
        val provider: String = prefs.getString("provider","No hay ").toString()

        setup(email ?: "", provider ?: "")

        val prefsEdit = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefsEdit.putString("email", "")
        prefsEdit.putString("provider", "")
        prefsEdit.apply()
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
        binding.emailTV.text = email
        binding.providerTV.text = provider

        binding.cerrarSesionBtn.setOnClickListener() {
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}