package com.example.bgproject.data

import androidx.room.TypeConverter
import com.example.bgproject.model.Result
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class ResultTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromResultList(resultList: List<Result>): String {
        return gson.toJson(resultList)
    }

    @TypeConverter
    fun toResultList(resultString: String): List<Result> {
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.fromJson(resultString, type)
    }
}
