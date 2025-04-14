package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.Item
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class order_history_viewmodel: ViewModel(){
    private val dbRef = FirebaseDatabase.getInstance().getReference("cart_items")
    val itemList: MutableLiveData<List<Item>> = MutableLiveData()

    fun fetchItems() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<Item>()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(Item::class.java)
                    item?.let { items.add(it) }
                }
                itemList.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}