package com.example.roomdatabase.data.repo

import androidx.lifecycle.LiveData
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.entity.user.UserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference

interface BillRepository {

    suspend fun getAllBills(): List<BillEntity1>

    suspend fun insertBill(bill: BillEntity1): Long

    suspend fun deleteBillById(billId: Int): Int

//    suspend fun createUser(user: UserEntity): Task<DocumentReference>

}