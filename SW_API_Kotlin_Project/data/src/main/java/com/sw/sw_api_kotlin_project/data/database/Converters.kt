package com.sw.sw_api_kotlin_project.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sw.sw_api_kotlin_project.data.network.model.Film
import com.sw.sw_api_kotlin_project.data.network.model.People
import com.sw.sw_api_kotlin_project.data.network.model.Planet

class Converters {
    @TypeConverter
    fun fromPeopleResults(value: String?): People? {
        val result = object : TypeToken<People?>() {}.type
        return Gson().fromJson(value, result)
    }

    @TypeConverter
    fun peopleToResults(people: People?): String {
        val gson = Gson()
        return gson.toJson(people)
    }

    @TypeConverter
    fun fromFilmResults(value: String?): Film? {
        val result = object : TypeToken<Film?>() {}.type
        return Gson().fromJson(value, result)
    }

    @TypeConverter
    fun dataToFilmResults(film: Film?): String {
        val gson = Gson()
        return gson.toJson(film)
    }

    @TypeConverter
    fun fromPlanetResults(value: String?): Planet? {
        val result = object : TypeToken<Planet?>() {}.type
        return Gson().fromJson(value, result)
    }

    @TypeConverter
    fun dataToPlanetResults(planet: Planet?): String {
        val gson = Gson()
        return gson.toJson(planet)
    }
}