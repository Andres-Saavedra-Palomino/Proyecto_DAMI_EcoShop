package com.example.proyecto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.proyecto.databinding.ActivityAuthBinding
import com.example.proyecto.ui.cuenta.ProviderType
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    private val GOOGLE_SIGN_IN = 101

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
        session()
    }

    override fun onStart() {
        super.onStart()
        binding.authLayout.visibility = View.VISIBLE
    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if(email != null && provider != null) {
            binding.authLayout.visibility = View.INVISIBLE
            verMain()
        }
    }

    private fun putSession(email: String, provider: ProviderType){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider.toString())
        prefs.apply()
    }

    private fun setup() {
        title = "Autenticación"

        binding.registrarBtn.setOnClickListener {
            if(binding.emailET.text.isNotEmpty() && binding.passwordET.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailET.text.toString(),
                    binding.passwordET.text.toString()).addOnCompleteListener { task: Task<AuthResult> ->

                    if (task.isSuccessful) {
                        putSession(task.result?.user?.email ?: "", ProviderType.BASIC)
                        verMain()
                    } else {
                        verAlerta()
                    }
                }
            }
        }
        binding.loginBtn.setOnClickListener {
            if(binding.emailET.text.isNotEmpty() && binding.passwordET.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.emailET.text.toString(),
                    binding.passwordET.text.toString()).addOnCompleteListener { task: Task<AuthResult> ->

                    if (task.isSuccessful) {
                        putSession(task.result?.user?.email ?: "", ProviderType.BASIC)
                        verMain()
                    } else {
                        verAlerta()
                    }
                }
            }
        }
        binding.googleBtn.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
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

    private fun verMain(){
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val cuenta = task.getResult(ApiException::class.java)

                if(cuenta != null) {
                    val credencial = GoogleAuthProvider.getCredential(cuenta.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credencial).addOnCompleteListener {
                        if (it.isSuccessful) {
                            putSession(cuenta.email,ProviderType.GOOGLE)
                            verMain()
                        } else {
                            verAlerta()
                        }
                    }
                }
            } catch (e: ApiException) {
                verAlerta()
            }

        }
    }

}