package com.cookandroid.invasion.Option.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1) // entity 정의, SQLite 버전 지정
abstract class ContactDatabase: RoomDatabase() {    // 실질적인 데이터베이스 인스턴스 생성, RoomDatabase 상속
    abstract fun contactDao(): ContactDao

    companion object {
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase? {
            if(INSTANCE == null) {
                synchronized(ContactDatabase::class) {    // 여러 스레드가 접근하지 못하도록 함
                    INSTANCE = Room.databaseBuilder(context.applicationContext, // 인스턴스 생성
                        ContactDatabase::class.java, "contact")
                        .fallbackToDestructiveMigration() // 데이터베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용
                        .build()
                }
            }
            return INSTANCE
        }
    }
}