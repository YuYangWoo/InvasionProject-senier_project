package com.cookandroid.invasion.Option.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact") // Contact라는 data class 생성, Entity 생성
data class Contact (
    @PrimaryKey(autoGenerate = true) // 기본키 id
    var id: Long?,

    @ColumnInfo(name = "number")
    var number: String
) {
    constructor() : this(null, "")
}