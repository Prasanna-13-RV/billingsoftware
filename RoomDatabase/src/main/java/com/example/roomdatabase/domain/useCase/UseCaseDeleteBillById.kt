package com.example.roomdatabase.domain.useCase

import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.repo.BillRepository
import javax.inject.Inject

class UseCaseDeleteBillById @Inject constructor(private val billRepository: BillRepository) {
    suspend fun deleteBillById(billId: Int): Int {
        return billRepository.deleteBillById(billId)
    }
}
