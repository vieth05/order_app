package com.example.product_Detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.order_app.R

class Egg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egg)
        val title=findViewById<TextView>(R.id.title_name)
        title.text="Egg"

    }
}
