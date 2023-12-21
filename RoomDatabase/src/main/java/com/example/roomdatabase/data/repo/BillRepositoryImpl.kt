package com.example.roomdatabase.data.repo

import com.example.roomdatabase.data.datasource.inter.BillDataSource
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.entity.user.UserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class BillRepositoryImpl @Inject constructor(
    private val billDataSource: BillDataSource,
//    private val userDataSource: UserDataSource
) :
    BillRepository {
    override suspend fun getAllBills(): List<BillEntity1> {
        return billDataSource.getAllBills()
    }

    override suspend fun insertBill(bill: BillEntity1): Long {
        return billDataSource.insertBill(bill)
    }

    override suspend fun deleteBillById(billId: Int): Int {
        return billDataSource.deleteBillById(billId)
    }

//    override suspend fun createUser(user: UserEntity): Task<DocumentReference> {
//        return userDataSource.createUser(user)
//    }
}