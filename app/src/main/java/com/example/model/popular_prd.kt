package com.example.model

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.example.Data.test
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object popular_prd {
    val popular = mutableListOf<test>()

    fun fetchProducts(progressBar: ProgressBar, callback: (List<test>) -> Unit) {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("popular_prd")

        progressBar.visibility = View.VISIBLE

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                popular.clear()
                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(test::class.java)
                    product?.let { popular.add(it) }
                }
                callback(popular)

                progressBar.visibility = View.GONE
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error fetching data: ${error.message}")
                progressBar.visibility = View.GONE
            }
        })
    }
}