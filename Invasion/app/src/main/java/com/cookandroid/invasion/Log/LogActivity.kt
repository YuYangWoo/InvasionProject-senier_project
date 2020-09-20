package com.cookandroid.invasion.Log

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cookandroid.invasion.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_log.*

class LogActivity : AppCompatActivity() {
    var abc = R.drawable.splashscreen
    var logList = arrayListOf<LogItem>(
        LogItem("fasd", "Fsdfsa", abc.toString()),
        LogItem("fasd", "Fsdfsa", abc.toString()),
        LogItem("fasd", "Fsdfsa", abc.toString())
    )

    var storage = Firebase.storage("gs://cerberus-8f761.appspot.com")

    // Create a storage reference from our app
    val storageRef = storage.reference

    // 하위 위치를 가리키는 참조

    val spaceRef = storageRef.child("cerb1/cue.jpg")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        // ActionBar Title 변경
        supportActionBar?.title = "알림"

        Log.d("test", logList.toString())

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this)
            .load(spaceRef)
            .into(img)
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