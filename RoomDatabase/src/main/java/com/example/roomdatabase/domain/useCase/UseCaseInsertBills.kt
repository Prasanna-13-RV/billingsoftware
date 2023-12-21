package com.example.roomdatabase.domain.useCase

import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.repo.BillRepository
import javax.inject.Inject

class UseCaseInsertBills @Inject constructor(private val billRepository: BillRepository) {

    private val bills = mutableListOf<BillEntity1>()
    suspend fun insertBill(bill: BillEntity1): Long {
        bills.add(bill)
        return billRepository.insertBill(bill)
    }

}
