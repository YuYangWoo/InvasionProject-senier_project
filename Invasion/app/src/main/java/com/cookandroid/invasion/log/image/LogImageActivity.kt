package com.cookandroid.invasion.log.image

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.invasion.R
import kotlinx.android.synthetic.main.activity_log_image.*

class LogImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_image)

        // ActionBar Title 변경
        supportActionBar?.title = "사진 더보기"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Intent를 사용해 LogFunction에서의 배열을 대입
        var allImage: Array<String> = intent.getStringArrayExtra("Image")!!
        gridViewImage.adapter = ImageAdapter(this, allImage)
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

