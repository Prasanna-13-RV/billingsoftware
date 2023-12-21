package com.example.roomdatabase.data.repo

import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.roomdatabase.data.entity.bill1.BillEntity1

class FakeRepository : BillRepository {

    val bills = mutableListOf(
        BillEntity1(
            1,
            "",
            1,
            "01-08-2023",
            "Chennai",
            "Chennai",
            listOf(
                BillData1(
                    1,
                    "Apple",
                    "2",
                    "10",
                    "20"
                )
            )
        )
    )

    override suspend fun getAllBills(): List<BillEntity1> {
        return bills
    }

    override suspend fun insertBill(bill: BillEntity1): Long {
        bills.add(bill)
        return 1L
    }

    override suspend fun deleteBillById(billId: Int): Int {
        val iterator = bills.iterator()
        while (iterator.hasNext()) {
            val bill = iterator.next()
            if (bill.billId == billId) {
                iterator.remove()
                return 1
            }
        }
        return 0
    }
}