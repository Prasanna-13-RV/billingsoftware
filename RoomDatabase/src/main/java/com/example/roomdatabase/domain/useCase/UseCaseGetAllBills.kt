package com.example.roomdatabase.domain.useCase

import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.data.repo.BillRepository
import javax.inject.Inject

class UseCaseGetAllBills @Inject constructor(private val billRepository: BillRepository) {
    suspend fun getAllBills(): List<BillEntity1> {
        return billRepository.getAllBills()
    }
}
