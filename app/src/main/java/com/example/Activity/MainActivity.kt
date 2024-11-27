package com.example.Activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.activity_createstore.MyStoreActiveFragment
import com.example.fragment.BrowserFragment
import com.example.fragment.HomeFragment
import com.example.fragment.MyStoreFragment
import com.example.fragment.order_history
import com.example.fragment.profile
import com.example.order_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val home_frm= HomeFragment()
        val store_frm=MyStoreFragment()
        val brose_frm= BrowserFragment()
        val oh_frm=order_history()
        val prf=profile()
        val kt=intent.getStringExtra("namme")
        if(kt != null){
            set_frm(MyStoreActiveFragment())
            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView.selectedItemId = R.id.store
        }
        else set_frm(home_frm)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.store ->
                {
                    if(kt != null){ val ti=findViewById<TextView>(R.id.title_name)
                        ti.text=kt
                        set_frm(MyStoreActiveFragment())
                    }
                    else set_frm(store_frm)
                }
                R.id.home -> set_frm(home_frm)
                R.id.browse -> set_frm(brose_frm)


                R.id.order -> set_frm(oh_frm)
                R.id.prf -> set_frm(prf)
            }
            true
        }
    }
    private fun set_frm(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }


}