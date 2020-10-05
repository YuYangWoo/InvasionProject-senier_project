package com.cookandroid.invasion.log.image

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ImageAdapter(var context: Context, var allImage:Array<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return allImage.size
    }

    override fun getItem(position: Int): Any? {
        return allImage[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var imageView = ImageView(context)

        // ImageView를 만든 객체의 크기와 사이즈 조절
        imageView.layoutParams = AbsListView.LayoutParams(500, 500)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER

        // 파이어베이스 storage를 참고해 사진 더보기 기능 구현
        var imageReference = Firebase.storage("gs://cerberus-592f9.appspot.com").reference.child("cerb1/" + allImage[position])
        imageReference.downloadUrl.addOnSuccessListener { Uri ->
            val imageURL = Uri.toString()
            Glide.with(context) // 띄어줄 뷰를 명시
                .load(imageURL) // 이미지 주소
                .into(imageView) // 사진의 size만큼 imageview를 만들어 띄우기
        }

        // 이미지뷰 클릭 이벤트
        imageView.setOnClickListener {
            var intent = Intent(context, BigImageActivity::class.java)
            intent.putExtra("allImage",allImage[position])
            Log.d("allImage", allImage[position])
            context.startActivity(intent)
        }
        return imageView
    }

}