package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Data.outData
import com.example.Datarepo.CartRepository
import com.example.order_app.databinding.ViewholderCheckoutCartBinding

class adapter_checkout_cart(
    private var items: MutableList<outData>,
    private val onItemRemoved: (Int) -> Unit
) : RecyclerView.Adapter<adapter_checkout_cart.ViewHolderCart>() {

    private val cartRepository = CartRepository()

    inner class ViewHolderCart(private val binding: ViewholderCheckoutCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: outData, key: String) {
            binding.txtCart.text = data.name
            binding.pricePrd.text = data.price

            Glide.with(binding.prdCart.context)
                .load(data.image)
                .into(binding.prdCart)

            binding.removePrd.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val price = data.price.toIntOrNull() ?: 0

                    // Xóa sản phẩm khỏi Firebase
                    cartRepository.deleteItem(
                        itemKey = key, // Truyền key từ Firebase
                        onSuccess = {
                            items.removeAt(position)
                            notifyItemRemoved(position)
                            onItemRemoved(price)
                        },
                        onFailure = { error ->
                            println("Error deleting item: ${error.message}")
                        }
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {
        val binding = ViewholderCheckoutCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderCart(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {
        val item = items[position]
        val key = item.key ?: ""  // Lấy key của item
        holder.bind(item, key)
    }

    override fun getItemCount() = items.size
}
