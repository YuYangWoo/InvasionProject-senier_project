package com.cookandroid.invasion.Option.RoomDB

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.room.*
import com.cookandroid.invasion.Option.RoomDB.Contact

@Dao
interface ContactDao { // SQL을 작성하기 위한 DAO 인터페이스
    @Query("SELECT * FROM contact ORDER BY number ASC")
    fun getAll(): LiveData<List<Contact>> // 전체 연락처 리스트 반환

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 중복 데이터 처리 지정
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}