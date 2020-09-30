package com.cookandroid.invasion.Option.Emergency

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cookandroid.invasion.Option.RoomDB.Contact
import com.cookandroid.invasion.Option.RoomDB.ContactRepository

    // AndroidViewModel 에서는 Application을 파라미터로 사용
class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
}