package com.sw.sw_api_kotlin_project.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sw.sw_api_kotlin_project.network.model.Film

@Entity(tableName = "film_table")
data class FavoriteFilm(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val film: Film,
    val registrationDate: String,
)