package com.example.roomdatabase.di

import com.example.roomdatabase.data.dao.BillDao
import com.example.roomdatabase.data.datasource.inter.BillDataSource
import com.example.roomdatabase.data.repo.BillRepository
import com.example.roomdatabase.data.repo.BillRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesBillRepository(
        billDataSource: BillDataSource,
//        userDataSource: UserDataSource
    ): BillRepository {
        return BillRepositoryImpl(
            billDataSource
//            , userDataSource
        )
    }
}