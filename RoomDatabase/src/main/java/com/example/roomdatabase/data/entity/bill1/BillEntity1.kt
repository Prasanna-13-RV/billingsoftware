package com.example.roomdatabase.data.entity.bill1

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.roomdatabase.utils.Converters

@Entity
@TypeConverters(Converters::class)
data class BillEntity1(
    @PrimaryKey(autoGenerate = true)
    var billId: Int = 0,
    var userId: String,
    var billNo: Int,
    var billingDate: String,
    var fromAddress: String,
    var toAddress: String,
    var billDescription: List<BillData1>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        mutableListOf<BillData1>().apply {
            parcel.readList(this, BillData1::class.java.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(billId)
        parcel.writeString(userId)
        parcel.writeInt(billNo)
        parcel.writeString(billingDate)
        parcel.writeString(fromAddress)
        parcel.writeString(toAddress)
        parcel.writeList(billDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BillEntity1> {
        override fun createFromParcel(parcel: Parcel): BillEntity1 {
            return BillEntity1(parcel)
        }

        override fun newArray(size: Int): Array<BillEntity1?> {
            return arrayOfNulls(size)
        }
    }
}
