package com.example.Activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.activity_createstore.MyStoreActiveFragment
import com.example.fragment.*
import com.example.order_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.admob.Manage_ad

class MainActivity : AppCompatActivity() {
    private var countAd: Int =0;
    private lateinit var bottomNavigationView: BottomNavigationView
    private var usernameFromIntent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Manage_ad.initMobileAds(this)
        Manage_ad.loadBannerAd(findViewById(R.id.adView))
        Manage_ad.loadAndShowInterstitial(this)
        initViews()
        handleIntentData()
        setupBottomNav()
        observeAppLifecycle()
    }

    private fun observeAppLifecycle() {
        if(countAd!=0)
        ProcessLifecycleOwner.get().lifecycle.addObserver(
            LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    Manage_ad.loadAndShowInterstitial(this)
                }
            }
        )
        countAd++;
    }
    private fun initViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        usernameFromIntent = intent.getStringExtra("namme")
    }

    // Xử lý dữ liệu từ intent khi mở app
    private fun handleIntentData() {
        if (usernameFromIntent != null) {
            setFragment(MyStoreActiveFragment())
            bottomNavigationView.selectedItemId = R.id.store
        } else {
            setFragment(HomeFragment())
        }
    }

    // Xử lý điều hướng bottom nav
    private fun setupBottomNav() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.store -> {
                        setFragment(MyStoreFragment())
                }
                R.id.home -> setFragment(HomeFragment())
                R.id.browse ->{
                    setFragment(BrowserFragment())
                    if(countAd==1 || countAd %5 ==0)
                    {
                        Manage_ad.loadAndShowInterstitial(this)
                    }
                    countAd++;

                }
                R.id.order -> setFragment(order_history())
                R.id.prf -> setFragment(profile())
            }
            true
        }
    }
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
