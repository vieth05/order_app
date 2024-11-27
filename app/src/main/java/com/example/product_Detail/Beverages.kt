package com.example.product_Detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Data.Product
import com.example.adapter.GridProductAdapter
import com.example.order_app.R
import com.example.order_app.databinding.BeveragesBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class beverages : AppCompatActivity() {

    private lateinit var binding: BeveragesBinding
    private val productList = mutableListOf<Product>()
    private lateinit var adapter: GridProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BeveragesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter
        adapter = GridProductAdapter(this, productList)

        // Set up RecyclerView
        binding.gvbeve.layoutManager = GridLayoutManager(this, 2)  // 2 columns
        binding.gvbeve.adapter = adapter

        // Fetch data from Firebase
        fetchDataFromFirebase()

        // Set up back button click listener
        binding.navi.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun fetchDataFromFirebase() {
        val database = FirebaseDatabase.getInstance().getReference("beverages")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()  // Clear the existing list before adding new data
                for (data in snapshot.children) {
                    val product = data.getValue(Product::class.java)
                    if (product != null) {
                        productList.add(product)
                    }
                }
                adapter.notifyDataSetChanged()  // Notify the adapter that the data has changed
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@beverages, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
