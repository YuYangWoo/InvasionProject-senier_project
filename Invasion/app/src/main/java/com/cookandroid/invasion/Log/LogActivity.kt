package com.cookandroid.invasion.Log

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.invasion.R
import kotlinx.android.synthetic.main.activity_log.*

class LogActivity : AppCompatActivity() {

    var logList = arrayListOf<LogItem>(
        LogItem("도둑이 침입했습니다!", "2020.09.16 18:00", "splashscreen"),
        LogItem("도둑이 침입했습니다!", "2020.09.16 18:00", "splashscreen"),
        LogItem("도둑이 침입했습니다!", "2020.09.16 18:00", "splashscreen"),
        LogItem("도둑이 침입했습니다!", "2020.09.16 18:00", "splashscreen"),
        LogItem("도둑이 침입했습니다!", "2020.09.16 18:00", "splashscreen")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        // ActionBar Title 변경
        supportActionBar?.title = "알림"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // LogAdapter 객체 생성 후 recyclerView와 연동
        val logAdapter = LogAdapter(this, logList)
        recyclerView.adapter = logAdapter

        // LinearLayoutManager 객체 생성 후 layoutManager에 대입
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
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