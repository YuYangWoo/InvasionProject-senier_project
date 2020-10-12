package com.cookandroid.invasion.log.image

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cookandroid.invasion.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_big_image.*

class BigImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 상태바를 없애는 코드 -> Full Screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_big_image)

        // ImageAdapter의 allImage를 intent로 가져와 image에 대입
        var image = intent.getStringExtra("allImage")
        var imageReference = Firebase.storage("gs://cerberus-592f9.appspot.com").reference.child("cerb1/" + image)
        imageReference.downloadUrl.addOnSuccessListener { Uri ->
            val imageURL = Uri.toString()

            Glide.with(this) // 띄어줄 뷰를 명시
                .load(imageURL) // 이미지 주소
                .into(bigImage) // log_function의 imageView에 띄우기
        }

    }
}