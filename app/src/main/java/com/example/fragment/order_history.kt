package com.example.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.activity_checkout.checkout_cart
import com.example.activity_checkout.order_success
import com.example.order_app.R
import com.example.order_app.Wishlist

class order_history : Fragment(R.layout.fragment_order_history) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

          val inflater=inflater.inflate(R.layout.fragment_order_history, container, false)
          val title=inflater.findViewById<TextView>(R.id.bar_title)
          title.text="Order History"

        val wl=inflater.findViewById<ImageView>(R.id.wish_icon)
        wl.setOnClickListener{
            startActivity(Intent(requireContext(), Wishlist::class.java))
        }
        val od=inflater.findViewById<ImageView>(R.id.order)
        od.setOnClickListener{
            startActivity(Intent(requireContext(), checkout_cart::class.java))
        }

        val deliv=inflater.findViewById<LinearLayout>(R.id.deliver)
        deliv.setOnClickListener{
            val intent=Intent(requireContext(), order_success::class.java)
            startActivity(intent)
        }
          return inflater
    }
}
