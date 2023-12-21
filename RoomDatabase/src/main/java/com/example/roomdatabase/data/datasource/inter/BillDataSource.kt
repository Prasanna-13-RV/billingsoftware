package com.example.roomdatabase.data.datasource.inter

import com.example.roomdatabase.data.entity.bill1.BillEntity1

interface BillDataSource {

    suspend fun getAllBills(): List<BillEntity1>

    suspend fun insertBill(bill: BillEntity1): Long

    suspend fun deleteBillById(billId: Int): Int

}