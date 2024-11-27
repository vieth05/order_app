package com.example.activity_auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.Activity.MainActivity
import com.example.order_app.R

class otp_ver : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.otp_verf)

        val next_Btn = findViewById<Button>(R.id.next_Btn)
        next_Btn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java) // Chuyển tới MainActivity
            startActivity(intent)
        }
    }
}
