package com.cookandroid.invasion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // BackpressCloseHandler 객체화
    private val backPressCloseHandler = BackPressCloseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // btnLog 클릭 이벤트
        btnLog.setOnClickListener {
            // LogActivity 호출
            startActivity(Intent(this, LogActivity::class.java))
        }

        // btnOption 클릭 이벤트
        btnOption.setOnClickListener {
            // OptionActivity 호출
            startActivity(Intent(this, OptionActivity::class.java))
        }
    }

    // Back 버튼을 눌렀을 때
    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }

}