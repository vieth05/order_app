package com.example.model

import android.util.Log
import com.example.Data.test
import com.google.firebase.database.*
import android.widget.ProgressBar
import android.view.View

object new_prd {
    val newprds = mutableListOf<test>()

    fun fetchProducts(progressBar: ProgressBar, callback: (List<test>) -> Unit) {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("products")

        progressBar.visibility = View.VISIBLE

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                newprds.clear()
                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(test::class.java)
                    product?.let { newprds.add(it) }
                }
                callback(newprds)

                progressBar.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error fetching data: ${error.message}")
                progressBar.visibility = View.GONE
            }
        })
    }
}
