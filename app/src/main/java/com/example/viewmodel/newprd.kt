package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Data.test
import com.google.firebase.database.*

class ProductViewMode : ViewModel() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("products")

    private val _products = MutableLiveData<List<test>>()
    val products: LiveData<List<test>> get() = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        _isLoading.value = true
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<test>()
                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(test::class.java)
                    product?.let { productList.add(it) }
                }
                _products.value = productList
                _isLoading.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                _isLoading.value = false
            }
        })
    }
}