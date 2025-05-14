package com.example.Activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.activity_createstore.MyStoreActiveFragment
import com.example.fragment.*
import com.example.order_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var bottomNavigationView: BottomNavigationView
    private var usernameFromIntent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAds()
        initViews()
        handleIntentData()
        setupBottomNav()
    }

    // Khởi tạo Google Mobile Ads
    private fun initAds() {
        MobileAds.initialize(this)
        loadBannerAd()
        loadInterstitialAd()
    }

    // Load quảng cáo banner
    private fun loadBannerAd() {
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    // Load quảng cáo Interstitial
    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                    mInterstitialAd?.show(this@MainActivity)
                }
            })
    }

    // Gán ID view và dữ liệu intent
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
                    if (usernameFromIntent != null) {
                        findViewById<TextView>(R.id.title_name)?.text = usernameFromIntent
                        setFragment(MyStoreActiveFragment())
                    } else {
                        setFragment(MyStoreFragment())
                    }
                }
                R.id.home -> setFragment(HomeFragment())
                R.id.browse ->{

                    setFragment(BrowserFragment())
                }
                R.id.order -> setFragment(order_history())
                R.id.prf -> setFragment(profile())
            }
            true
        }
    }

    // Hàm thay fragment
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
