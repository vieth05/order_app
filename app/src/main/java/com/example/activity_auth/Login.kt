package com.example.activity_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.Activity.MainActivity
import com.example.order_app.databinding.LoginActivityBinding
import com.example.viewmodel.LoginViewModel

class login : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Quan sát LiveData từ ViewModel
        observeViewModel()

        // Xử lý sự kiện click đăng nhập
        val loginBtn=binding.loginBtn
        val edt_mail=binding.edtMail
        val edt_pass=binding.edtPass
        val txtreg=binding.txtreg
        loginBtn.setOnClickListener {
            val email = edt_mail.text.toString()
            val password = edt_pass.text.toString()
            viewModel.login(email, password)
        }

        // Xử lý sự kiện chuyển sang màn hình đăng ký
        txtreg.setOnClickListener {
            startActivity(Intent(this, signup::class.java))
        }
    }

    private fun observeViewModel() {
        // Quan sát trạng thái đăng nhập
        viewModel.loginStatus.observe(this) { success ->
            if (success) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        // Quan sát lỗi đăng nhập
        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
