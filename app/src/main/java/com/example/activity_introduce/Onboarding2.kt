package com.example.activity_introduce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.order_app.R
import com.example.order_app.databinding.Onboarding2Binding

class onboarding2:AppCompatActivity()
{
    private lateinit var binding: Onboarding2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= Onboarding2Binding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.nextbtn2.setOnClickListener{
            val intent: Intent=Intent(applicationContext, onboarding3::class.java)
            startActivity(intent)
        }
    }
}