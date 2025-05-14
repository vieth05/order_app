package com.example.admob
import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
object Manage_ad {
    private var interstitialAd: InterstitialAd? = null

    fun initMobileAds(activity: Activity) {
        MobileAds.initialize(activity)
    }

    fun loadBannerAd(adView: AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    fun loadAndShowInterstitial(activity: Activity) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(activity, "ca-app-pub-3940256099942544/1033173712", adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    interstitialAd?.show(activity)
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.d("AdManager", "InterstitialAd failed to load: ${error.message}")
                }
            })
    }
}