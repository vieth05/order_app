package com.example.activity_introduce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.activity_auth.login
import com.example.order_app.R
import com.example.order_app.databinding.Onboarding3Binding

class onboarding3:AppCompatActivity()
{
    private lateinit var binding: Onboarding3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=Onboarding3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nextbtn3.setOnClickListener{
            val intent: Intent=Intent(applicationContext, login::class.java)
            startActivity(intent)
        }
    }
}