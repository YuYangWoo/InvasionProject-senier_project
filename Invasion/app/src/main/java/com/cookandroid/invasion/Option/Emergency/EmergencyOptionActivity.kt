package com.cookandroid.invasion.Option.Emergency

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.invasion.Option.RoomDB.Contact
import com.cookandroid.invasion.R
import kotlinx.android.synthetic.main.activity_optioncall.*

class EmergencyOptionActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstaceState: Bundle?) {

        super.onCreate(savedInstaceState)
        setContentView(R.layout.activity_optioncall)

        // ActionBar 타이틀 변경
        supportActionBar?.title = "설정"

        // ActionBar Home 버튼 Enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // contactItemClick & contactItemLongClick 람다 지정
        // contact 정보 & EmergencyOptionAddActivity의 나머지 불러오기
        val adapter = ContactAdapter({ contact ->
            // 클릭 시 편집 기능 전부 주석 처리
            // val intent = Intent(this, EmergencyOptionAddActivity::class.java)
            // intent.putExtra(EmergencyOptionAddActivity.EXTRA_CONTACT_NUMBER, contact.number)
            // intent.putExtra(EmergencyOptionAddActivity.EXTRA_CONTACT_ID, contact.id)
            // startActivity(intent)

        }, { contact ->
            deleteDialog(contact)
        })

        // RecyclerView Adapter와 LayoutManager를 만들어 Recyclerview와 연결
        val lm = LinearLayoutManager(this)
        recyclerview_main_list.adapter = adapter
        recyclerview_main_list.layoutManager = lm
        recyclerview_main_list.setHasFixedSize(true)

        // contactViewModel 초기화
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        // 뷰모델의 Observer의 Onchanged에 Adapter를 통해 UI를 바로 업데이트하도록 지정
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })

        // 데이터 추가 버튼 눌렀을 시 이벤트
        button_main_insert.setOnClickListener {
            val intent = Intent(this, EmergencyOptionAddActivity::class.java)
            startActivity(intent)

        }
    }

        // 길게 눌렀을 시 데이터 삭제
    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("정말 지우시겠습니까?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
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