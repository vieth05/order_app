package com.example.activity_createstore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.order_app.R

class MyStoreActiveFragment : Fragment(R.layout.mystore_active) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.mystore_active, container, false)
        val title = view.findViewById<TextView>(R.id.bar_title)
        title.text = "My Store"
        val ed_store=view.findViewById<AppCompatButton>(R.id.ed_store)
        ed_store.setOnClickListener{
            val intent=Intent(requireContext(), createstore::class.java)
            startActivity(intent)
        }
        return view
    }
}
