package com.cookandroid.invasion.Option.Emergency

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.cookandroid.invasion.Option.RoomDB.Contact
import com.cookandroid.invasion.R
import kotlinx.android.synthetic.main.activity_call_add.*

class EmergencyOptionAddActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_add)

        // ViewModel 객체를 만듦
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        // 만약 intent가 null이 아니고 extra에 번호가 들어있다면 EditText와 id값 지정
        // 클릭 시 편집 기능 전부 주석처리
        // if (intent != null && intent.hasExtra(EXTRA_CONTACT_NUMBER)) {
            // add_et_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            // id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        // }

        // Done 버튼을 통해 EditText의 null 체크를 한 후 ViewModel을 통해 insert후 EmergencyOptionActivity로 돌아감
        // id값이 null일 경우 Room에서 자동으로 id 생성, 새로운 contact를 DB에 추가(DAO에서 OnConflictStrategy를 Replace로 설정해뒀기 때문)
        add_btn.setOnClickListener {
            val number = add_et_number.text.toString()

            if (number.isEmpty()) {
                Toast.makeText(this, "Please enter number", Toast.LENGTH_SHORT).show()
            } else {
                val contact = Contact(id, number)
                contactViewModel.insert(contact)
                finish()
            }
        }
    }

    // intent extra로 사용할 상수
    // 클릭 시 편집 기능 전부 주석 처리
    // companion object {
        // const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        // const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    // }
}