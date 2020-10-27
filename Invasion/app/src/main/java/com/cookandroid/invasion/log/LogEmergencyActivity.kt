package com.cookandroid.invasion.log

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.invasion.Option.Emergency.ContactAdapter
import com.cookandroid.invasion.Option.Emergency.ContactViewModel
import com.cookandroid.invasion.Option.Emergency.EmergencyOptionAddActivity
import com.cookandroid.invasion.Option.RoomDB.Contact
import com.cookandroid.invasion.Option.RoomDB.ContactDatabase
import com.cookandroid.invasion.R
import kotlinx.android.synthetic.main.activity_logemergency.*
import kotlinx.android.synthetic.main.list_logemergency.*
import kotlinx.android.synthetic.main.list_optioncall.*

class LogEmergencyActivity : AppCompatActivity(){

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logemergency)

        supportActionBar?.title = "비상연락망"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val adapter = ContactAdapter({ contact ->
            val intent = Intent(Intent.ACTION_DIAL)
            val tel = "tel:" + contact.number
            intent.data = Uri.parse(tel)
            startActivity(intent)
        }, { contact ->
        })

        val lm = LinearLayoutManager(this)
        logemergency_main_list.adapter = adapter
        logemergency_main_list.layoutManager = lm
        logemergency_main_list.setHasFixedSize(true)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })

    }

}
