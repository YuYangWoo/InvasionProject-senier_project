package com.cookandroid.invasion.log

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.cookandroid.invasion.Option.Emergency.EmergencyOptionActivity
import com.cookandroid.invasion.R
import com.cookandroid.invasion.log.image.LogImageActivity
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_log_function.*
import kotlin.arrayOf as arrayOf1


class LogFunction : AppCompatActivity(){

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_function)

        // ActionBar Title 변경
        supportActionBar?.title = "기능"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 현재 시간 LogFunction의 TextView에 적용
        var currentTime = intent.getStringExtra("logTime")!!.toString()
        txtTime.text = currentTime

        // recyclerView의 사진을 크게 띄우기
        var nowImage: Array<String> = intent.getStringArrayExtra("logPhoto")!!
        var imageReference = Firebase.storage("gs://cerberus-592f9.appspot.com").reference.child("cerb1/" + nowImage[0])
        imageReference.downloadUrl.addOnSuccessListener { Uri ->
            val imageURL = Uri.toString()

            Glide.with(this) // 띄어줄 뷰를 명시
                .load(imageURL) // 이미지 주소
                .into(imgDetail) // log_function의 imageView에 띄우기
        }

        // 비상연락 버튼을 눌렀을 때
        btnEmergency.setOnClickListener {
            startActivity(Intent(this, LogEmergencyActivity::class.java))
        }

        // 경보음 버튼을 눌렀을 때
        btnSiren.setOnClickListener {
            // 파이어베이스 데이터베이스 연동
            database = FirebaseDatabase.getInstance()
            // DB 테이블 연결
            databaseReference = database.getReference("cerberusTable").child("alarm")

            // Dialog창 Title과 Message 설정
            val builder = AlertDialog.Builder(this)
            builder.setTitle("알림").setMessage("사이렌을 울리려면 켜기 끄려면 끄기를 누르세요")

            // 켜기 버튼을 누르면 값을 1로 바꾼다
            builder.setPositiveButton("켜기") { _: DialogInterface, _: Int ->
                databaseReference.setValue(1)
                Toast.makeText(this, "경보음이 울렸습니다.", Toast.LENGTH_SHORT).show()
            }

            // 끄기 버튼을 누르면 값을 0으로 바꾼다.
            builder.setNegativeButton("끄기") { _: DialogInterface, _: Int ->
                databaseReference.setValue(0)
                Toast.makeText(this, "경보음을 껐습니다.", Toast.LENGTH_SHORT).show()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        // 더보기 버튼을 눌렀을 때
        btnShowImage.setOnClickListener {
            var intent = Intent(this, LogImageActivity::class.java)
            intent.putExtra("Image", nowImage)
            ContextCompat.startActivity(this, intent, null)
        }

        // 현관확인 버튼을 눌렀을 때
        btnConfirm.setOnClickListener {
            // DB RTDB에 접근해 cerberusTable의 door값의 reference를 참조
            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("cerberusTable").child("door")

            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                // data를 가져오는 메서드
                override fun onDataChange(snapshot: DataSnapshot) {
                    var doorValue = snapshot.value
                    if(doorValue is Double)
                        doorValue = doorValue.toInt()
                    else if(doorValue is Float)
                        doorValue = doorValue.toInt()

                    when(doorValue) {
                        0 -> Toast.makeText(applicationContext, "현관문이 닫혀있습니다.", Toast.LENGTH_LONG).show()
                        1 -> Toast.makeText(applicationContext, "현관문이 열려있습니다.", Toast.LENGTH_LONG).show()
                        else -> Log.d("Error", "에러!!")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Error", "에러!!")
                }

            })
        }

        // 비상잠금 버튼을 눌렀을 때
        btnLock.setOnClickListener {
            // 파이어베이스 데이터베이스 연동
            database = FirebaseDatabase.getInstance()
            // DB 테이블 연결
            databaseReference = database.getReference("cerberusTable").child("doorlock")

            // Dialog창 Title과 Message 설정
            val builder = AlertDialog.Builder(this)
            builder.setTitle("알림").setMessage("도어락 전원차단을 원하면 차단 전원 On을 원하면 On을 누르세요")

            // 켜기 버튼을 누르면 값을 1로 바꾼다
            builder.setPositiveButton("차단") { _: DialogInterface, _: Int ->
                databaseReference.setValue(1)
                Toast.makeText(this, "전원이 차단 되었습니다.", Toast.LENGTH_SHORT).show()
            }

            // 끄기 버튼을 누르면 값을 0으로 바꾼다.
            builder.setNegativeButton("On") { _: DialogInterface, _: Int ->
                databaseReference.setValue(0)
                Toast.makeText(this, "전원이 켜졌습니다.", Toast.LENGTH_SHORT).show()
            }

            val alertDialog = builder.create()
            alertDialog.show()
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
