package com.sw.sw_api_kotlin_project.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sw.sw_api_kotlin_project.network.model.Planet

@Entity(tableName = "planet_table")
data class FavoritePlanet(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val planet: Planet,
    val registrationDate: String,
)