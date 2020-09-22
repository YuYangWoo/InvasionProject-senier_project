package com.cookandroid.invasion.log

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.invasion.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_log.*

class LogActivity : AppCompatActivity() {
    private lateinit var logAdapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var logList: ArrayList<LogItem>
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        // ActionBar Title 변경
        supportActionBar?.title = "알림"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // LinearLayoutManager 객체 생성 후 layoutManager에 대입 및 recyclerView 고정크기 On
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        logList = ArrayList()
        // 파이어베이스 데이터베이스 연동
        database = FirebaseDatabase.getInstance();

        // DB 테이블 연결
        databaseReference = database.getReference("logList");

        // logList에 DB데이터 연결
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                logList.clear() // 기존 배열리스트가 존재하지않게 초기화

                for (snapshot in dataSnapshot.children) { // 반복문으로 데이터 List를 추출해냄
                    val log = snapshot.getValue(LogItem::class.java) // 만들어뒀던 객체에 데이터를 담는다.
                    logList.add(log!!) // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                logAdapter.notifyDataSetChanged() // 리스트 저장 및 새로고침해야 반영이 됨
            }
            // 디비를 가져오던중 에러 발생 시 에러문 출력
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Error", databaseError.toException().toString())
            }
        })

        logAdapter = CustomAdapter(logList, this)
        recyclerView.adapter = logAdapter // 리사이클러뷰에 어댑터 연결
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