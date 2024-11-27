package com.example.activity_introduce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.order_app.R
import com.example.order_app.databinding.Onboarding1Binding

class Onboarding1 : AppCompatActivity() {
    private lateinit var binding: Onboarding1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=Onboarding1Binding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.nextbtn1.setOnClickListener{
            val intent:Intent=Intent(applicationContext, onboarding2::class.java)
            startActivity(intent)
        }
    }
}