package com.sw.sw_api_kotlin_project.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sw.sw_api_kotlin_project.network.model.People

@Entity(tableName = "people_table")
data class FavoritePeople(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val people: People,
    val registrationDate: String,
)