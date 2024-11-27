package com.example.order_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Data.Product
import com.example.adapter.GridProductAdapter
import com.example.order_app.databinding.WishlishBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Wishlist : AppCompatActivity() {
    private lateinit var binding: WishlishBinding
    private lateinit var adapter: GridProductAdapter
    private val productList = mutableListOf<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=WishlishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navi.titleName.text="Wishlish"
        adapter= GridProductAdapter(this, productList)
        binding.gvwl.layoutManager = GridLayoutManager(this, 2)  // 2 columns
        binding.gvwl.adapter = adapter
        val database= FirebaseDatabase.getInstance().getReference("wishlist")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                for (data in snapshot.children){
                    val product=data.getValue(Product::class.java)
                    if (product!= null){
                        productList.add(product)
                    }
                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Wishlist, "Error: ${error.message}", Toast.LENGTH_SHORT).show()            }

        })
        adapter = GridProductAdapter(this, productList)
        binding.gvwl.adapter=adapter
        binding.navi.iconBack.setOnClickListener{
            finish()
        }
    }
}
