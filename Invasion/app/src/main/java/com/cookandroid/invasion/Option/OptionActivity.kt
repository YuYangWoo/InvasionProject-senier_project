package com.cookandroid.invasion.Option

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.invasion.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_optioncall.*
import kotlinx.android.synthetic.main.list_option.*

class OptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_option)

        // ActionBar Title 변경
        supportActionBar?.title = "설정"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val emergencyIntent = Intent(this, EmergencyOptionActivity::class.java)

        Optionbtn1.setOnClickListener {
            startActivity(emergencyIntent)
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