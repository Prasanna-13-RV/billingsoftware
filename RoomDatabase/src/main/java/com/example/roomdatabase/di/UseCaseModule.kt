package com.example.roomdatabase.di

import com.example.roomdatabase.data.repo.BillRepository
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
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetAllBillsUseCase(
        billRepository: BillRepository
    ): UseCaseGetAllBills {
        return UseCaseGetAllBills(billRepository)
    }

    @Singleton
    @Provides
    fun providesInsertBillsUseCase(
        billRepository: BillRepository
    ): UseCaseInsertBills {
        return UseCaseInsertBills(billRepository)
    }

    @Singleton
    @Provides
    fun providesUseCaseDeleteBillByIdUseCase(
        billRepository: BillRepository
    ): UseCaseDeleteBillById {
        return UseCaseDeleteBillById(billRepository)
    }

}