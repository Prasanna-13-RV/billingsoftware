package com.example.roomdatabase.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.roomdatabase.data.entity.bill1.BillEntity1

@Dao
interface BillDao {

    @Query("Select * from BillEntity1")
    fun getAllBills(): List<BillEntity1>

    @Upsert
    suspend fun insertBill(bill: BillEntity1): Long

    @Query("DELETE FROM BillEntity1 WHERE billId = :billId")
    suspend fun deleteBillById(billId: Int): Int

}