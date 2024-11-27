package com.example.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.activity_checkout.checkout_cart
import com.example.activity_createstore.createstore
import com.example.order_app.R
import com.example.order_app.Wishlist

class MyStoreFragment : Fragment(R.layout.my_store) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.my_store, container, false)
        val title=view.findViewById<TextView>(R.id.bar_title)
        title.text="My Store"
        val wl=view.findViewById<ImageView>(R.id.wish_icon)
        wl.setOnClickListener{
            startActivity(Intent(requireContext(), Wishlist::class.java))
        }
        val od=view.findViewById<ImageView>(R.id.order)
        od.setOnClickListener{
            startActivity(Intent(requireContext(), checkout_cart::class.java))
        }


        val btn= view.findViewById<Button>(R.id.btnCre)
        btn.setOnClickListener{
            startActivity(Intent(requireContext(), createstore::class.java))
        }

        return view
    }
}
