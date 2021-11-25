package com.example.proyecto.ui.playa

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.model.Catalogo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_playa.view.*

class CatalogoCell(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun AsignarValores(catalogo:Catalogo){
        val imgView:ImageView=itemView.findViewById(R.id.imgView)
        Picasso.get()
            .load(catalogo.imagen)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(imgView)

        val tvNombre:TextView=itemView.findViewById(R.id.tvNombre)
        tvNombre.text=catalogo.categoria

        val tvDescripcion:TextView=itemView.findViewById(R.id.tvDescripcion)
        tvDescripcion.text=catalogo.descripcion

        val tvPrecio:TextView=itemView.findViewById(R.id.tvPrecio)
        tvPrecio.text=catalogo.precio
    }
}