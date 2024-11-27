package com.example.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.activity_checkout.checkout_cart
import com.example.activity_auth.login
import com.example.order_app.R
import com.example.order_app.Wishlist

class profile : Fragment(R.layout.fragment_profile) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf=inflater.inflate(R.layout.fragment_profile, container, false)
        val wl=inf.findViewById<ImageView>(R.id.wish_icon)
        wl.setOnClickListener{
            startActivity(Intent(requireContext(), Wishlist::class.java))
        }
        val od=inf.findViewById<ImageView>(R.id.order)
        od.setOnClickListener{
            startActivity(Intent(requireContext(), checkout_cart::class.java))
        }
        val title=inf.findViewById<TextView>(R.id.bar_title)
        title.text="Profile"

      val log=inf.findViewById<TextView>(R.id.logout)

        log.setOnClickListener{
            val intent=Intent(requireContext(), login::class.java)
            startActivity(intent)
        }
      return inf
    }

}
