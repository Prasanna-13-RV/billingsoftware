package com.example.roomdatabase.data.entity.bill1

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class BillData1(
    @PrimaryKey(autoGenerate = true)
    var billDescriptionId: Int = 0,
    var billDescription1: String,
    var billDescription2: String,
    var billDescription3: String,
    var billDescription4: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(billDescriptionId)
        parcel.writeString(billDescription1)
        parcel.writeString(billDescription2)
        parcel.writeString(billDescription3)
        parcel.writeString(billDescription4)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BillData1> {
        override fun createFromParcel(parcel: Parcel): BillData1 {
            return BillData1(parcel)
        }

        override fun newArray(size: Int): Array<BillData1?> {
            return arrayOfNulls(size)
        }
    }
}
