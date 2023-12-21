package com.example.billgenerator.di

import com.example.billgenerator.viewmodel.AIDLViewModel
import com.example.roomdatabase.viewmodel.BillViewModel
import com.example.roomdatabase.domain.useCase.UseCaseDeleteBillById
import com.example.roomdatabase.domain.useCase.UseCaseGetAllBills
import com.example.roomdatabase.domain.useCase.UseCaseInsertBills
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Singleton
    @Provides
    fun providesViewModel(
        useCaseGetAllBills: UseCaseGetAllBills,
        useCaseInsertBills: UseCaseInsertBills,
        useCaseDeleteBillById: UseCaseDeleteBillById,
//        useCaseCreateUserById: UseCaseCreateUserById
    ): BillViewModel {
        return BillViewModel(
            useCaseGetAllBills,
            useCaseInsertBills,
            useCaseDeleteBillById
//            useCaseCreateUserById
        )
    }

    @Singleton
    @Provides
    fun providesAIDLViewModel(): AIDLViewModel {
        return AIDLViewModel()
    }
}
