package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.Data.Product
import com.example.model.browse_repo

class browse_viewmodel: ViewModel() {
    private val repository = browse_repo()
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun fetchProducts() {
        _isLoading.value = true
        repository.fetchProducts(
            callback = { products ->
                _productList.value = products
                _isLoading.value = false
            },
            onError = {
                _isLoading.value = false
            }
        )
    }
}