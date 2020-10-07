package com.cookandroid.invasion.Option.AppInfo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.invasion.R
import kotlinx.android.synthetic.main.activity_appinfo.*

class AppInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appinfo)

        // ActionBar 타이틀 변경
        supportActionBar?.title = "앱 정보"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 개인정보 처리방침 버튼 이벤트
        Infobtn4.setOnClickListener {
            startActivity(Intent(this, PersonalInfoActivity::class.java))
        }

        // 오픈소스 라이선스 버튼 이벤트
        Infobtn5.setOnClickListener {
            startActivity(Intent(this, OpenSourceActivity::class.java))
        }
    }
        // ActionBar ItemSelect 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
