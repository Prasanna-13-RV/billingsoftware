package com.example.roomdatabase.data.datasource

import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.datasource.inter.BillDataSource
import com.example.roomdatabase.data.repo.BillRepository
import com.example.roomdatabase.data.dao.BillDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BillDataSourceImpl @Inject constructor(private val billDao: BillDao) :
    BillDataSource {
    override suspend fun getAllBills(): List<BillEntity1> {
        return withContext(Dispatchers.IO) {
            billDao.getAllBills()
        }
    }

    override suspend fun insertBill(bill: BillEntity1): Long {
        return withContext(Dispatchers.IO) {
            billDao.insertBill(bill)
        }
    }

    override suspend fun deleteBillById(billId: Int): Int {
        return withContext(Dispatchers.IO) {
            billDao.deleteBillById(billId)
        }
    }
}