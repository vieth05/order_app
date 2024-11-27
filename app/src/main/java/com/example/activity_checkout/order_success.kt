package com.example.activity_checkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Activity.MainActivity
import com.example.order_app.R
import com.example.order_app.databinding.OrderSuccessBinding

class order_success:AppCompatActivity() {
    private lateinit var binding: OrderSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= OrderSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navi.cancle.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}