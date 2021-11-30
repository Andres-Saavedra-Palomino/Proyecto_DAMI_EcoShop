package com.example.proyecto.ui.buscar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.model.Catalogo

class CatalogoListAdapter(var catalogo:ArrayList<Catalogo>, val context: Context): RecyclerView.Adapter<CatalogoCell>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoCell {
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val  cell_catalogo: View = inflater.inflate(R.layout.cell_catalogo, parent, false)
        return CatalogoCell(cell_catalogo)
    }

    override fun onBindViewHolder(holder: CatalogoCell, position: Int) {
        holder.AsignarValores(catalogo.get(position))
    }

    override fun getItemCount(): Int {
        return catalogo.size
    }
}