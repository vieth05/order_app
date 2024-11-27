package com.example.activity_checkout

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.order_app.R
import com.example.order_app.databinding.AddCartBinding

class add_cart : AppCompatActivity() {

    private lateinit var binding: AddCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPay.setOnClickListener{
            startActivity(Intent(this, order_success::class.java))

        }
        setup()
    }

    private fun setup() {
        binding.navi.titleName.text = "Add Cart"
        val back = findViewById<ImageView>(R.id.icon_back)
        back.setOnClickListener { finish() }

        // Set up listener for the image view (imgCart)
        binding.imgCart.setOnClickListener {
            // Open gallery to pick an image
            openGallery()
        }
    }

    // Open the gallery to select an image
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    // Handle the result after selecting an image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            data?.data?.let { uri ->
                // Load the image using Glide into imgCart
                Glide.with(this)
                    .load(uri)
                    .into(binding.imgCart)
            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
