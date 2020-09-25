package com.cookandroid.invasion

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_log.*
import kotlinx.android.synthetic.main.activity_option.*

class OptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        // ActionBar Title 변경
        supportActionBar?.title = "설정"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var storage = Firebase.storage("gs://cerberus-8f761.appspot.com")

        // Create a storage reference from our app
        val storageRef = storage.reference

        // 하위 위치를 가리키는 참조

        val spaceRef = storageRef.child("cerb1/cue.jpg")

        Glide.with(this)
            .load("gs://cerberus-8f761.appspot.com/cerb1/cue.jpg")
            .into(img)
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