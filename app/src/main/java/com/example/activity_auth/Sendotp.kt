package com.example.activity_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.order_app.R

class sendotp:AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sendotp)
        val loginBtn=findViewById<Button>(R.id.nextBtn)
        loginBtn.setOnClickListener{
            val intent: Intent=Intent(applicationContext, otp_ver::class.java)
            startActivity(intent)
        }

    }
}