package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.Item
import com.example.order_app.databinding.VhdProcessBinding

class adapter_ordeerhistory(private val itemList: MutableList<Item>) : RecyclerView.Adapter<adapter_ordeerhistory.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: VhdProcessBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                name.text = item.name
                price.text = item.price
                Glide.with(root.context).load(item.image).into(image)
            }
        }
    }

    fun updateData(newItemList: List<Item>) {
        itemList.clear()
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VhdProcessBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}
