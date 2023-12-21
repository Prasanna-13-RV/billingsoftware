package com.example.roomdatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.domain.useCase.UseCaseDeleteBillById
import com.example.roomdatabase.domain.useCase.UseCaseGetAllBills
import com.example.roomdatabase.domain.useCase.UseCaseInsertBills
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(
    private val useCaseGetAllBills: UseCaseGetAllBills,
    private val useCaseInsertBills: UseCaseInsertBills,
    private val useCaseDeleteBillById: UseCaseDeleteBillById,
//    private val useCaseCreateUserById: UseCaseCreateUserById
) : ViewModel() {

    private val _bills = MutableLiveData<List<BillEntity1>>()
    val bills: LiveData<List<BillEntity1>> get() = _bills

    val myValue = "Login"

    fun getBills() {
        viewModelScope.launch {
            _bills.value = useCaseGetAllBills.getAllBills()
        }
    }

    fun insertBills(bill: BillEntity1) {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseInsertBills.insertBill(bill)
//            _bills.postValue(useCaseGetAllBills.getAllBills())
            getBills()
        }
    }

    fun deleteBillById(billId: Int) {
        viewModelScope.launch {
            useCaseDeleteBillById.deleteBillById(billId)
            getBills()
        }
    }

}