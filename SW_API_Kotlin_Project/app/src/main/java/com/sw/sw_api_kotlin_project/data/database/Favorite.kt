package com.sw.sw_api_kotlin_project.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sw.sw_api_kotlin_project.data.model.Film
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.utils.ListType

//TODO ジェネリクス型に変更できないか調査
@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val listType: ListType,
    val people: People?,
    val film: Film?,
    val planet: Planet?
)
