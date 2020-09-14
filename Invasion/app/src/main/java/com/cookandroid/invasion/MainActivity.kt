package com.cookandroid.invasion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
}