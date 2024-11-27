package com.example.activity_checkout

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.order_app.R

class payment_option: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_payment_option)
        setup()
        next()
    }
    private fun setup(){
        val title=findViewById<TextView>(R.id.title_name)
        title.text="Payment Option"
        val back=findViewById<ImageView>(R.id.icon_back)
        back.setOnClickListener{ finish()}
        val add=findViewById<TextView>(R.id.txt)
        val one_price=findViewById<TextView>(R.id.one_price)
        one_price.text=intent.getStringExtra("one_price")
        val all_price=findViewById<TextView>(R.id.all_price)
        all_price.text=intent.getStringExtra("full_price")

        val bund=intent.getStringExtra("title")
        add.text=bund
    }
    private fun next(){
        val btn=findViewById<ImageView>(R.id.add_pay)
        btn.setOnClickListener{
            val intent=Intent(applicationContext, add_cart::class.java)
            startActivity(intent)
        }
    }
}