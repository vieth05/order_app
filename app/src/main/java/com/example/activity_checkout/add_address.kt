package com.example.activity_checkout

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.order_app.R

class add_address:AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addnew_address)
        val title=findViewById<TextView>(R.id.title_name)
        title.text="Add a new address"
        val back=findViewById<ImageView>(R.id.icon_back)
        back.setOnClickListener{
            val intent=Intent(applicationContext, checkout_cart::class.java)
            startActivity(intent)
        }
        val save=findViewById<Button>(R.id.btnSave)
        save.setOnClickListener{
            val zipcode =findViewById<EditText>(R.id.zipcode).text.toString()
            val name =findViewById<EditText>(R.id.name).text.toString()
            val city =findViewById<EditText>(R.id.city).text.toString()
            val state=findViewById<EditText>(R.id.state).text.toString()
            val combinedText = "Deliver to $name, $zipcode\n$city,$state"
            val intent = Intent().apply {
                putExtra("combinedText", combinedText)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}