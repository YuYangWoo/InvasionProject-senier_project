package com.cookandroid.invasion

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(){

    private val splashTime:Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 2초 지나고 화면 전환
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },splashTime)
    }
}