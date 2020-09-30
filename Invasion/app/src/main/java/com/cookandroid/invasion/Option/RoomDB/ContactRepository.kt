package com.cookandroid.invasion.Option.RoomDB

import android.app.Application
import androidx.lifecycle.LiveData
import com.cookandroid.invasion.Option.RoomDB.Contact
import com.cookandroid.invasion.Option.RoomDB.ContactDao
import com.cookandroid.invasion.Option.RoomDB.ContactDatabase
import java.lang.Exception

class ContactRepository(application: Application) {
    // DB 인스턴스 호출해 사용
    // Database, Dao, contacts 각각 초기화
    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }
    fun insert(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.insert(contact) })
            thread.start()
            } catch (e: Exception) { }
        }
    fun delete(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.delete(contact)
            })
            thread.start()
        } catch (e: Exception) { }
    }

}