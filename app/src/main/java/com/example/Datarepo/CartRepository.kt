package com.example.Datarepo

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.Data.outData

class CartRepository {
    private val database = FirebaseDatabase.getInstance().getReference("cart_items")

    fun saveItem(item: outData, onSuccess: () -> Unit, onFailure: (DatabaseError) -> Unit) {
        val key = database.push().key

        if (key != null) {
            database.child(key).setValue(item)
                .addOnSuccessListener {
                    item.key = key
                    database.child(key).setValue(item)
                    onSuccess()
                }
                .addOnFailureListener { exception ->
                    onFailure(DatabaseError.fromException(exception))
                }
        } else {
            onFailure(DatabaseError.fromException(Exception("Failed to generate key.")))
        }
    }


    fun getItems(onSuccess: (List<outData>) -> Unit, onFailure: (DatabaseError) -> Unit) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.mapNotNull { dataSnapshot ->
                    val item = dataSnapshot.getValue(outData::class.java)
                    item
                }
                onSuccess(items)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error)
            }
        })
    }

    fun deleteItem(itemKey: String, onSuccess: () -> Unit, onFailure: (DatabaseError) -> Unit) {
        database.child(itemKey).removeValue()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(DatabaseError.fromException(exception))
            }
    }

}
