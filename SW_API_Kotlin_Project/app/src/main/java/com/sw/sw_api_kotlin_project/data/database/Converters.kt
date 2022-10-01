package com.sw.sw_api_kotlin_project.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sw.sw_api_kotlin_project.data.model.People

class Converters {
    @TypeConverter
    fun fromResults(value: String?): People {
        val result = object : TypeToken<People?>() {}.type
        return Gson().fromJson(value, result)
    }

    @TypeConverter
    fun fromResults(people: People): String {
        val gson = Gson()
        return gson.toJson(people)
    }
}