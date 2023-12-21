package com.example.billgenerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.domain.useCase.UseCaseDeleteBillById
import com.example.roomdatabase.domain.useCase.UseCaseGetAllBills
import com.example.roomdatabase.domain.useCase.UseCaseInsertBills
import com.example.roomdatabase.viewmodel.BillViewModel

class BillViewModelFactory(
    private val useCaseGetAllBills: UseCaseGetAllBills,
    private val useCaseInsertBills: UseCaseInsertBills,
    private val useCaseDeleteBillById: UseCaseDeleteBillById
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BillViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BillViewModel(useCaseGetAllBills, useCaseInsertBills, useCaseDeleteBillById) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}