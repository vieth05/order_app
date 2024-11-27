package com.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Data.Product
import com.example.order_app.R

class GridProductAdapter(
    private val context: Context,
    private val productList: List<Product>
) : RecyclerView.Adapter<GridProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_grid, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // Bind data to the views
        Glide.with(context)
            .load(product.image)
            .override(400, 400)
            .into(holder.imageView)

        holder.nameTextView.text = product.name
        holder.priceTextView.text = product.price
    }

    override fun getItemCount(): Int = productList.size

    // ViewHolder class to hold views
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.img_view)
        val nameTextView: TextView = view.findViewById(R.id.name)
        val priceTextView: TextView = view.findViewById(R.id.price)
    }
}