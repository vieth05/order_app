package com.example.product_Detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.order_app.R

class Fruit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fruit)
        val title=findViewById<TextView>(R.id.title_name)
        title.text="Fruit"
        val backIcon = findViewById<ImageView>(R.id.icon_back)

    }
}
