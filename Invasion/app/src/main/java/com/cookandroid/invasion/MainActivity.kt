package com.cookandroid.invasion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.invasion.log.LogActivity
import com.cookandroid.invasion.log.LogItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {
    private var logList: ArrayList<LogItem> = ArrayList()
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    // BackpressCloseHandler 객체화
    private val backPressCloseHandler = BackPressCloseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 메인화면 알람 개수

        // 파이어베이스 데이터베이스 연동
        database = FirebaseDatabase.getInstance()

        // DB 테이블 연결
        databaseReference = database.getReference("LogList")

        // logList에 DB데이터 연결
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                logList.clear() // 기존 배열리스트가 존재하지않게 초기화

                for (snapshot in dataSnapshot.children) { // 반복문으로 데이터 List를 추출해냄
                    val log = snapshot.getValue(LogItem::class.java) // 만들어뒀던 객체에 데이터를 담는다.
                    logList.add(log!!) // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                txtNumber.append(logList.size.toString())
            }

            // 디비를 가져오던중 에러 발생 시 에러문 출력
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Error", databaseError.toException().toString())
            }

        })

        // btnLog 클릭 이벤트
        btnLog.setOnClickListener {
            // LogActivity 호출
            var logIntent = Intent(this, LogActivity::class.java)
            logIntent.putExtra("logList", logList)
            startActivity(logIntent)
        }

        // btnOption 클릭 이벤트
        btnOption.setOnClickListener {
            // OptionActivity 호출
            startActivity(Intent(this, OptionActivity::class.java))
        }

    }

    // Back 버튼을 눌렀을 때
    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }

}