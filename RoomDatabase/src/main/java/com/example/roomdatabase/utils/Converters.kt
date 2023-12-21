package com.example.roomdatabase.utils

import androidx.room.TypeConverter
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<BillData1>? {
        if (value == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<BillData1>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<BillData1>?): String {
        return Gson().toJson(list)
    }
}