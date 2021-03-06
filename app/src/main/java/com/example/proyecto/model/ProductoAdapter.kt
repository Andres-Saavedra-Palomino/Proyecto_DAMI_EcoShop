package com.example.proyecto.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.databinding.ItemProductoRecyclerviewBinding
import com.squareup.picasso.Picasso

class ProductoAdapter(private val lista: List<Producto>) :
  RecyclerView.Adapter<ProductoAdapter.ItemHolder>() {
  class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemProductoRecyclerviewBinding.bind(itemView)

    //Agregar mas campos aqui!
    fun bind(producto: Producto) {
      binding.codigoProducto.text= producto.categoria
      binding.nombreProducto.text= producto.descripcion
      binding.descuentoProducto.text= "S/ ${(producto.precio.toString().toDouble() - producto.descuento.toString().toDouble())}"
      Picasso.get()
        .load(producto.imagen)
        .error(R.drawable.ic_baseline_error_outline_24)
        .into(binding.imagenProducto)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
    val layoutInflater:LayoutInflater= LayoutInflater.from((parent.context))

    return ItemHolder(layoutInflater.inflate(R.layout.item_producto_recyclerview,parent,false))
  }

  override fun onBindViewHolder(holder: ItemHolder, position: Int) {
    val item: Producto = lista[position]
    holder.bind(item)
  }

  override fun getItemCount(): Int {
    return lista.size
  }

}