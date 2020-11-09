package com.cookandroid.invasion.log

import android.os.Parcel
import android.os.Parcelable

class LogItem() : Parcelable{
    var logInfo: String? = null
    var logPhoto: String? = null
    var logTime: String? = null

    constructor(parcel: Parcel) : this() {
        logInfo = parcel.readString()
        logPhoto = parcel.readString()
        logTime = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(logInfo)
        parcel.writeString(logPhoto)
        parcel.writeString(logTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LogItem> {
        override fun createFromParcel(parcel: Parcel): LogItem {
            return LogItem(parcel)
        }

        override fun newArray(size: Int): Array<LogItem?> {
            return arrayOfNulls(size)
        }
    }


}