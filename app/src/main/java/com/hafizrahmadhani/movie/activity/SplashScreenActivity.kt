package com.hafizrahmadhani.movie.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.hafizrahmadhani.movie.R
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashDuration = 4000
        Handler(Looper.getMainLooper()).postDelayed({
            val splashIntent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            this@SplashScreenActivity.startActivity(splashIntent)
            finish()
        }, splashDuration.toLong())
    }
}