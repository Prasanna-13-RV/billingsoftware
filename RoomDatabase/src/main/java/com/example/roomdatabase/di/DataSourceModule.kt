package com.example.roomdatabase.di

import com.example.roomdatabase.data.dao.BillDao
import com.example.roomdatabase.data.datasource.BillDataSourceImpl
import com.example.roomdatabase.data.datasource.inter.BillDataSource
import com.example.roomdatabase.data.repo.BillRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideBillDataSource(billDataSourceImpl: BillDataSourceImpl): BillDataSource
}