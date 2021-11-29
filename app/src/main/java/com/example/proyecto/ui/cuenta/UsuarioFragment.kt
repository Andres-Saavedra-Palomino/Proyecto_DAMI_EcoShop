package com.example.proyecto.ui.cuenta

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto.AuthActivity
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentUsuarioBinding
import com.example.proyecto.model.ProductoActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType {
  BASIC,
  GOOGLE
}

class UsuarioFragment : Fragment() {

  private lateinit var usuarioviewModel: UsuarioViewModel
  private var _binding: FragmentUsuarioBinding? = null
  private val db = FirebaseFirestore.getInstance()

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    usuarioviewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
    _binding = FragmentUsuarioBinding.inflate(inflater, container, false)

    val root: View = binding.root

    val prefs = this.requireContext().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)

    val email: String = prefs.getString("email", "Email no encontrado").toString()
    val provider: String = prefs.getString("provider","No hay proveedor").toString()

    setup(email ?: "", provider ?: "")

    val prefsEdit = this.requireContext().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
    prefsEdit.putString("email", email)
    prefsEdit.putString("provider", provider)
    prefsEdit.apply()

    binding.btnUsuarioRegistrarProducto.setOnClickListener {
      val x = Intent(this.requireActivity(),ProductoActivity::class.java)
      startActivity(x)
    }

    return root
  }

  private fun setup(email: String, provider: String) {
    binding.emailTV.text = email
    binding.providerTV.text = provider

    getCuenta(email)

    binding.cerrarSesionBtn.setOnClickListener() {
      val prefs= this.requireContext().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
      prefs.clear()
      prefs.apply()

      FirebaseAuth.getInstance().signOut()

      val x = Intent(requireActivity(), AuthActivity::class.java)
      requireActivity().startActivity(x)
    }
  }

  private fun getCuenta(email:String) {
    db.collection("Usuario").document(email).get().addOnSuccessListener {
      binding.nombresTV.setText(it.get("nombre") as String?)
      binding.apellidosTV.setText(it.get("apellido") as String?)
      binding.nroDocumentoTV.setText(it.get("documento") as String?)
      binding.telefonoTV.setText(it.get("telefono") as String?)
      binding.fechaNacTV.setText(it.get("fecha_nac") as String?)
      val idrol: String = it.get("id_rol") as String

      if (idrol == "2") {
        binding.btnUsuarioRegistrarProducto.isClickable=false
        binding.btnUsuarioRegistrarProducto.visibility=View.INVISIBLE
      }
    }
  }

}