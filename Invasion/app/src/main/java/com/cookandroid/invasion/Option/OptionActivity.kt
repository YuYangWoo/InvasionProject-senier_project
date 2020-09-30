package com.cookandroid.invasion

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.invasion.Option.AppInfo.AppInfoActivity
import com.cookandroid.invasion.Option.Emergency.EmergencyOptionActivity
import kotlinx.android.synthetic.main.activity_option.*

class OptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        // ActionBar Title 변경
        supportActionBar?.title = "설정"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 비상 연락망 수정 버튼 눌렀을 때 이벤트
        btn_emergency.setOnClickListener {
            startActivity(Intent(this, EmergencyOptionActivity::class.java))
        }

        // 앱 정보 버튼 눌렀을 때 이벤트
        btn_appinfo.setOnClickListener {
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