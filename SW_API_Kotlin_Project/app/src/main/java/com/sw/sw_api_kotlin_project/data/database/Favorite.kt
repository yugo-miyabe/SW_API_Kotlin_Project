package com.sw.sw_api_kotlin_project.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sw.sw_api_kotlin_project.data.model.People

//TODO ジェネリクス型に変更
@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val people: People
)
