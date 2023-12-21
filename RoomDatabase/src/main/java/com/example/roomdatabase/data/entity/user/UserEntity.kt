package com.example.roomdatabase.data.entity.user

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.utils.Converters

data class UserEntity(
    val userId: String?,
    val userName: String,
    val userEmail: String,
    val userPassword: String,
    val phoneNumber: Int?,
    val userAddress: String?,
    val profileImage: String?,
    val userBills: List<BillEntity1>?
)
