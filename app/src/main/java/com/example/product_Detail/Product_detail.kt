package com.example.product_Detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.activity_checkout.checkout_cart
import com.example.order_app.R
import com.example.order_app.databinding.ProductDetailBinding
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig

class ProductDetail : AppCompatActivity() {
    private lateinit var binding: ProductDetailBinding
    private lateinit var remoteConfig: FirebaseRemoteConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        remoteConfig = Firebase.remoteConfig

        blackfriday()
        val prdName = intent.getStringExtra("text") ?: "Unknown" // Tên sản phẩm
        val prdImg = intent.getStringExtra("img") ?: "" // URL hoặc ID ảnh
        val prdPrice = intent.getStringExtra("price") ?: "0" // Giá sản phẩm

        // Hiển thị dữ liệu lên giao diện
        binding.txtPrd.text = prdName
        binding.priceNew.text = prdPrice

        Glide.with(this)
            .load(prdImg)
            .override(410, 230)
            .into(binding.imgPrd)
        // Xử lý sự kiện click vào nút like
        binding.like.setOnClickListener {
            addToWishlist(prdName, prdImg, prdPrice)
        }

        // Xử lý sự kiện quay lại
        binding.back.setOnClickListener {
            finish()
        }
        binding.addBtn.setOnClickListener{
            val intent=Intent(this, checkout_cart::class.java)
            intent.putExtra("product_name",prdName )
            intent.putExtra("product_img", prdImg)
            intent.putExtra("price", prdPrice)
            startActivity(intent)
        }
    }

    private fun addToWishlist(name: String, image: String, price: String) {
        // Tham chiếu đến Firebase
        val databaseRef = FirebaseDatabase.getInstance().getReference("wishlist")

        // Lấy danh sách sản phẩm hiện có từ "wishlist"
        databaseRef.get().addOnSuccessListener { dataSnapshot ->
            var exists = false

            // Duyệt qua danh sách sản phẩm để kiểm tra trùng lặp
            for (item in dataSnapshot.children) {
                val existingName = item.child("name").getValue(String::class.java)
                if (existingName == name) {
                    exists = true
                    break
                }
            }

            if (exists) {
                // Sản phẩm đã tồn tại trong wishlist
                Toast.makeText(this, "Product is already in the Wishlist!", Toast.LENGTH_SHORT).show()
            } else {
                // Tạo một key duy nhất cho sản phẩm mới
                val key = databaseRef.push().key
                if (key != null) {
                    val product = mapOf(
                        "name" to name,
                        "image" to image,
                        "price" to price
                    )

                    // Thêm sản phẩm mới vào Firebase
                    databaseRef.child(key).setValue(product)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Added to Wishlist!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { error ->
                            Toast.makeText(this, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Error generating key!", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Failed to access wishlist: ${error.message}", Toast.LENGTH_SHORT).show()
        }
    }
    private fun blackfriday(){remoteConfig.fetchAndActivate()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("RemoteConfig", "Config updated: ${task.result}")
                val salesMessage = remoteConfig.getString("sales")
                if (salesMessage.isNotEmpty()) {
                    binding.sales.text = salesMessage
                } else {
                    Log.w("RemoteConfig", "Key 'sales' is empty or not available.")
                }
            } else {
                Log.e("RemoteConfig", "Fetch failed: ${task.exception}")
            }
        }


    }
}
