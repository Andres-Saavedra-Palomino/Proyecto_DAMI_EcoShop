package com.example.proyecto.ui.carrito

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.databinding.ItemCarritoRecyclerviewBinding
import com.squareup.picasso.Picasso

class CarritoAdapter(private val lista: List<Carrito>,val quitar: Quitar) :
    RecyclerView.Adapter<CarritoAdapter.ItemHolder>() {
    class ItemHolder(itemView: View,quitar: Quitar) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val binding = ItemCarritoRecyclerviewBinding.bind(itemView)
        var quitar: Quitar?=null
        fun bind(carrito: Carrito) {
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/navagioecoshop.appspot.com/o/NavagioPlaya.jpg?alt=media&token=ed8668ab-9739-46bf-b47f-3eb45c80eb1f").error(R.drawable.ic_baseline_error_outline_24)
                .into(binding.imgItem)
            binding.codProd.text=carrito.codigo
            binding.nomProd.text=carrito.nombre
            binding.preProd.text=carrito.precio
            binding.cantProd.text=carrito.cantidad
        }
        override fun onClick(v: View?) {
            this.quitar?.onClick(adapterPosition,v!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater:LayoutInflater= LayoutInflater.from((parent.context))
        return ItemHolder(layoutInflater.inflate(R.layout.item_carrito_recyclerview,parent,false),quitar)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item: Carrito = lista[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}