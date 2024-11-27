package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Data.outData
import com.example.Data.test
import com.example.order_app.databinding.ViewholderNewproductListBinding

class adapter_newprd(
    private var ds: List<test>,
    private val onItemClick: (test) -> Unit
) : RecyclerView.Adapter<adapter_newprd.ViewHolderNewPrd>() {

    inner class ViewHolderNewPrd(private val binding: ViewholderNewproductListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: test) {
            binding.txtView.text = data.name
            binding.pricePrd.text = data.price

            Glide.with(binding.imgView.context)
                .load(data.image)
                .override(400, 400)
                .into(binding.imgView)

            binding.root.setOnClickListener {
                onItemClick(data) // Truyền dữ liệu item khi được click
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNewPrd {
        val binding = ViewholderNewproductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderNewPrd(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderNewPrd, position: Int) {
        holder.bind(ds[position])
    }

    override fun getItemCount(): Int = ds.size

    fun updateData(newData: List<test>) {
        ds = newData
        notifyDataSetChanged()
    }
}

