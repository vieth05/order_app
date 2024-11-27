package com.example.activity_createstore

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.Activity.MainActivity
import com.example.order_app.R

class createstore: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_store)
        val title=findViewById<TextView>(R.id.title_name)
        title.text="My Store"
        val back=findViewById<ImageView>(R.id.icon_back)
        back.setOnClickListener{
          finish()
        }
        val name=findViewById<EditText>(R.id.namee)
        val kq=name.text.toString()
        val create=findViewById<AppCompatButton>(R.id.btn)
        create.setOnClickListener{
           val intent=Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("namme",kq)
            startActivity(intent)
        }
    }
}