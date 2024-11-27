package com.example.product_Detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Data.Product
import com.example.adapter.GridProductAdapter
import com.example.order_app.databinding.BreadAndBakeryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BreadAndBakeryActivity : AppCompatActivity() {
    private lateinit var binding: BreadAndBakeryBinding
    private val productList= mutableListOf<Product>()
    private lateinit var adapter: GridProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=BreadAndBakeryBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.navi.titleName.text = "Bread & Bakery"
        adapter = GridProductAdapter(this, productList)

        // Set up RecyclerView
        binding.gvbb.layoutManager = GridLayoutManager(this, 2)  // 2 columns
        binding.gvbb.adapter = adapter
        val database= FirebaseDatabase.getInstance().getReference("bread")
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
                Toast.makeText(this@BreadAndBakeryActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()            }

        })
        adapter = GridProductAdapter(this, productList)
        binding.gvbb.adapter=adapter
        binding.navi.iconBack.setOnClickListener{
            finish()
        }
    }
}
