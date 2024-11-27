package com.example.product_Detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Data.Product
import com.example.adapter.GridProductAdapter
import com.example.order_app.databinding.PetCareBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PetCare : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var binding: PetCareBinding
    private val productList= mutableListOf<Product>()
    private lateinit var adapter: GridProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= PetCareBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.navi.titleName.text="Pet Care"
        adapter = GridProductAdapter(this, productList)

        // Set up RecyclerView
        binding.gvpetcare.layoutManager = GridLayoutManager(this, 2)  // 2 columns
        binding.gvpetcare.adapter = adapter
    val database= FirebaseDatabase.getInstance().getReference("petcare")
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
                Toast.makeText(this@PetCare, "Error: ${error.message}", Toast.LENGTH_SHORT).show()            }

        })
        adapter = GridProductAdapter(this, productList)
        binding.gvpetcare.adapter=adapter
        binding.navi.iconBack.setOnClickListener{
            finish()
        }

    }
}
