package com.sw.sw_api_kotlin_project.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.network.model.Planet

@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val listType: ListType,
    val people: People?,
    val film: Film?,
    val planet: Planet?,
    val registrationDate: String
)
