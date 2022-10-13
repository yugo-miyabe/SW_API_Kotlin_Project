package com.sw.sw_api_kotlin_project.utils

import com.sw.sw_api_kotlin_project.data.database.Favorite

interface ListDetailsDatabase {
    suspend fun insert(favorite: Favorite)
    suspend fun delete(favorite: Favorite)
    suspend fun isFavoriteExist(name: String): Favorite?
}