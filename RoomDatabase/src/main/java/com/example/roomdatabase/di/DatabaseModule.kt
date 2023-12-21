package com.example.roomdatabase.di

import android.app.Application
import androidx.room.Room
import com.example.roomdatabase.data.dao.BillDao
import com.example.roomdatabase.data.database.BillDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesBillRoomDatabase(context: Application): BillDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BillDatabase::class.java,
            "billDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun providesBillRoomDao(billDatabase: BillDatabase): BillDao {
        return billDatabase.billDao()
    }
}