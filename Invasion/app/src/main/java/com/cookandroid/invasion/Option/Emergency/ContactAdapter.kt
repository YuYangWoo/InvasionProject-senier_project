package com.cookandroid.invasion.Option.Emergency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.invasion.Option.RoomDB.Contact
import com.cookandroid.invasion.R

    // contactItemClick, contactItemLongClick 형태로 클릭했을 때와 롱클릭했을 때의 액션을 각각
    // EmergencyOptionActivity에 넘겨줌
class ContactAdapter(val contactItemClick: (Contact) -> Unit, val contactItemLongClick: (Contact) -> Unit)
    : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    private var contacts: List<Contact> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_optioncall, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(contacts[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val numberTv = itemView.findViewById<TextView>(R.id.txtnumber)

        fun bind(contact: Contact) {
            numberTv.text = contact.number

            itemView.setOnClickListener {
                contactItemClick(contact)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(contact)
                true
            }
        }
    }

    // View에서 화면을 갱신할 때 사용할 setContacts 함수
    // DB가 변경될 때마다 이 함수 호출
    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

}
