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

        supportActionBar?.title = "앱 정보"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

       // Infobtn4.setOnClickListener {
           // startActivity(Intent(this, PersonalInfoActivity::class.java))
       // }

    }

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
