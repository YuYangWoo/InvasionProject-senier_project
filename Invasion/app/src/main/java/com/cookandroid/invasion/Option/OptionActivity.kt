package com.cookandroid.invasion

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cookandroid.invasion.Option.AppInfo.AppInfoActivity
import com.cookandroid.invasion.Option.Emergency.EmergencyOptionActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_log.*
import kotlinx.android.synthetic.main.activity_option.*
import kotlinx.android.synthetic.main.list_option.*

class OptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_option)

        // ActionBar Title 변경
        supportActionBar?.title = "설정"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Optionbtn1.setOnClickListener {
            startActivity(Intent(this, EmergencyOptionActivity::class.java))
        }
        Optionbtn2.setOnClickListener {
            startActivity(Intent(this, AppInfoActivity::class.java))
        }
    }

    // ActionBar ItemSelected 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 뒤로가기 버튼
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}