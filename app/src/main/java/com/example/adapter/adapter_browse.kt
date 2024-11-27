package com.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Data.Product
import com.example.order_app.R

class BrowseAdapter(
    private val context: Context,
    private var productList: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<BrowseAdapter.ProductViewHolder>(), Filterable {

    private var filteredList: List<Product> = productList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_grid, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = filteredList[position]

        Glide.with(context)
            .load(product.image)
            .override(400, 400)
            .into(holder.imageView)

        holder.nameTextView.text = product.name
        holder.priceTextView.text = product.price

        holder.itemView.setOnClickListener {
            onItemClick(product) //
        }
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().lowercase()
                val filteredResults = FilterResults()

                filteredResults.values = if (query.isEmpty()) {
                    productList
                } else {
                    productList.filter { product ->
                        product.name.lowercase().contains(query)
                    }
                }
                return filteredResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Product>
                notifyDataSetChanged()
            }
        }
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.img_view)
        val nameTextView: TextView = view.findViewById(R.id.name)
        val priceTextView: TextView = view.findViewById(R.id.price)
    }
}
