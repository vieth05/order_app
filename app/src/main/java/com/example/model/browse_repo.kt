package com.example.model

import com.example.Data.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class browse_repo {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val browseReference = firebaseDatabase.getReference("browse")

    fun fetchProducts(callback: (List<Product>) -> Unit, onError: () -> Unit) {
        browseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val products = mutableListOf<Product>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(Product::class.java)
                    if (item != null) {
                        products.add(item)
                    }
                }
                callback(products)
            }

            override fun onCancelled(error: DatabaseError) {
                onError()
            }
        })
    }
}