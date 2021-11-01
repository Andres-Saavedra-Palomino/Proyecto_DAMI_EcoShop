package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.proyecto.databinding.ActivityAuthBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Eventos personalizados a Goggle Analytics
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        //Setup
        setup()
    }

    private fun setup() {
        title = "Autenticación"

        binding.registrarBtn.setOnClickListener {
            auth = Firebase.auth
            if(binding.emailET.text.isNotEmpty() && binding.passwordET.text.isNotEmpty()) {
                auth
                    .createUserWithEmailAndPassword(binding.emailET.text.toString(),
                        binding.passwordET.text.toString()).addOnCompleteListener { task: Task<AuthResult> ->

                        if (task.isSuccessful) {
                            verHome(task.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            verAlerta()
                        }
                    }
            }
        }
        binding.loginBtn.setOnClickListener {
            auth = Firebase.auth
            if(binding.emailET.text.isNotEmpty() && binding.passwordET.text.isNotEmpty()) {
                auth
                    .signInWithEmailAndPassword(binding.emailET.text.toString(),
                        binding.passwordET.text.toString()).addOnCompleteListener { task: Task<AuthResult> ->

                        if (task.isSuccessful) {
                            verHome(task.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            verAlerta()
                        }
                    }
            }
        }
    }

    private fun verAlerta() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de autenticación.")
        builder.setPositiveButton("Aceptar", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun verHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}