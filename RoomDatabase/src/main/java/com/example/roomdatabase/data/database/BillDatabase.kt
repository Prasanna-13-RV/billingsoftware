package com.example.roomdatabase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdatabase.data.dao.BillDao
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.roomdatabase.utils.Converters

@Database(entities = [BillEntity1::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BillDatabase : RoomDatabase() {
    abstract fun billDao(): BillDao
}