package com.cookandroid.invasion.log

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.cookandroid.invasion.Option.Emergency.EmergencyOptionActivity
import com.cookandroid.invasion.R
import com.cookandroid.invasion.log.image.LogImageActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_log_function.*

class LogFunction : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_function)

        // ActionBar Title 변경
        supportActionBar?.title = "알림"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 현재 시간 functionActivity에 적용
        var currentTime = intent.getStringExtra("logTime")!!.toString()
        txtTime.text = currentTime

        // recyclerView의 사진을 크게 띄우기
        var nowImage: Array<String> = intent.getStringArrayExtra("logPhoto")!!
        var imageReference = Firebase.storage("gs://cerberus-8f761.appspot.com").reference.child("cerb1/" + nowImage[0])
        imageReference.downloadUrl.addOnSuccessListener { Uri ->
            val imageURL = Uri.toString()

            Glide.with(this) // 띄어줄 뷰를 명시
                .load(imageURL) // 이미지 주소
                .into(imgDetail) // log_function의 imageView에 띄우기
        }

        // 비상연락 버튼을 눌렀을 때
        btnEmergency.setOnClickListener {
            startActivity(Intent(this, EmergencyOptionActivity::class.java))
        }

        // 더보기 버튼을 눌렀을 때
        btnShowImage.setOnClickListener {
            var intent = Intent(this, LogImageActivity::class.java)
            intent.putExtra("Image", nowImage)
            ContextCompat.startActivity(this, intent, null)
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
