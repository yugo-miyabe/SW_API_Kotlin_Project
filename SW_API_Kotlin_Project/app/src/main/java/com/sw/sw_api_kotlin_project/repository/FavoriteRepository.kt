package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.database.FavoriteDao

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    suspend fun insert(favorite: Favorite) = favoriteDao.insert(favorite)

    suspend fun delete(favorite: Favorite) = favoriteDao.delete(favorite)

    // TODO Peopleのみに修正
    suspend fun getPeopleFavoriteState(name: String) = favoriteDao.getFavorite(name = name)

    fun getAll() = favoriteDao.getAll()
}