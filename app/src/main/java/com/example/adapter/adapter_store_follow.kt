package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Data.data_store_follow
import com.example.Data.outData
import com.example.order_app.databinding.ViewholderNewproductListBinding
import com.example.order_app.databinding.ViewholderStoreFlowBinding

class adapter_store_follow(private var ds: List<data_store_follow>) : RecyclerView.Adapter<adapter_store_follow.ViewHolderstore>() {

    inner class ViewHolderstore(private val binding: ViewholderStoreFlowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: data_store_follow) {
            binding.txtView.text = data.name
            binding.imgView.setImageResource(data.img)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderstore {
        val binding = ViewholderStoreFlowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderstore(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderstore, position: Int) {
        holder.bind(ds[position])
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}
