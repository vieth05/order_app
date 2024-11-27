package com.example.activity_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.order_app.databinding.SignupActivityBinding
import com.google.firebase.auth.FirebaseAuth

class signup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : SignupActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= SignupActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Khởi tạo các phần tử giao diện
        val btnCre = binding.btnCre
        val back_1 = binding.back1Button
        val emailInput = binding.edtEmail
        val passwordInput = binding.edtPassw
        val repassInput = binding.edtRepass

        // Thiết lập sự kiện click cho nút đăng ký
        btnCre.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val repass = repassInput.text.toString()

            if (isValidInput(email, password, repass)) {
                createUser(email, password)
            }
        }
    }
    // Kiểm tra tính hợp lệ của dữ liệu nhập vào
    private fun isValidInput(email: String, password: String, repass: String): Boolean {
        return when {
            email.isEmpty() || password.isEmpty() || repass.isEmpty() -> {
                showToast("Please fill all fields")
                false
            }
            password != repass -> {
                showToast("Passwords do not match")
                false
            }
            else -> true
        }
    }

    // Tạo tài khoản người dùng
    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, login::class.java))
                    finish()
                } else {
                    showToast("Sign Up Failed: ${task.exception?.message}")
                }
            }
    }

    // Hàm hiển thị thông báo Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
