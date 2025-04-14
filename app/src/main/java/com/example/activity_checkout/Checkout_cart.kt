package com.example.activity_checkout

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Data.outData
import com.example.Datarepo.CartRepository
import com.example.adapter.adapter_checkout_cart
import com.example.order_app.R
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import com.example.Datarepo.mycart_data

class checkout_cart : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_cart)
        val title = findViewById<TextView>(R.id.title_name)
        title.text = "My Cart"
        setupView()
        back()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val combinedText = result.data?.getStringExtra("combinedText") ?: ""
                val addAdrLayout = findViewById<FrameLayout>(R.id.add_adr)
                val newView = layoutInflater.inflate(R.layout.add_addres, null)
                newView.findViewById<TextView>(R.id.txt).text = combinedText
                addAdrLayout.removeAllViews()
                addAdrLayout.addView(newView)
                if (mycart_data.data_cart != null) {
                    val btnPay: AppCompatButton = findViewById(R.id.btn_pay)
                    btnPay.alpha = 1.0f
                    btnPay.isClickable = true
                }
            }
        }

        findViewById<FrameLayout>(R.id.add_adr).setOnClickListener {
            openActivity2()
        }
        nextpayment()
    }

    private fun nextpayment() {
        val pay = findViewById<AppCompatButton>(R.id.btn_pay)
        pay.setOnClickListener {
            intent = Intent(this, payment_option::class.java)
            intent.putExtra("title", findViewById<TextView>(R.id.txt).text.toString())
            intent.putExtra("one_price", findViewById<TextView>(R.id.one_price).text.toString())
            intent.putExtra("full_price", findViewById<TextView>(R.id.all_price).text.toString())
            startActivity(intent)
        }
    }

    private fun openActivity2() {
        val intent = Intent(this, add_address::class.java)
        launcher.launch(intent)
    }

    fun setupView() {
        val items = mutableListOf<outData>()
        val cartRepository = CartRepository()

        val prdName = intent.getStringExtra("product_name") ?: "Unknown Product"
        val prdImg = intent.getStringExtra("product_img") ?: ""
        val price = intent.getStringExtra("price") ?: "0"

        if (prdName.isNotBlank() && prdImg.isNotBlank() && price.isNotBlank()) {
            val newItem = outData(prdImg, prdName, price)
            cartRepository.saveItem(newItem,
                onSuccess = { println("Product saved successfully!") },
                onFailure = { e -> println("Error saving product: ${e.message}") }
            )
            val adapter=adapter_checkout_cart(items){}
            val recyclerView = findViewById<RecyclerView>(R.id.rvcart)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        // Load dữ liệu từ Firebase
        cartRepository.getItems(
            onSuccess = { firebaseItems ->
                items.clear()
                items.addAll(firebaseItems)

                val totalPrice = items.sumOf { it.price.toIntOrNull() ?: 0 }

                val onePrice = findViewById<TextView>(R.id.one_price)
                val fullPrice = findViewById<TextView>(R.id.all_price)
                onePrice.text = price
                fullPrice.text = totalPrice.toString()

                val adapter = adapter_checkout_cart(items) { itemPrice ->
                    val updatedPrice = items.sumOf { item -> item.price.toIntOrNull() ?: 0 }
                    fullPrice.text = updatedPrice.toString()
                }

                val recyclerView = findViewById<RecyclerView>(R.id.rvcart)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            },
            onFailure = { e -> println("Error fetching cart items: ${e.message}") }
        )
    }

    private fun back() {
        val btnBack = findViewById<ImageView>(R.id.icon_back)
        btnBack.setOnClickListener {
            finish()
        }
    }
}



//