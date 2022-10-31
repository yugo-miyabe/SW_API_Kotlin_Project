package com.sw.sw_api_kotlin_project.model.repository

import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.database.FavoriteDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {
    suspend fun add(favorite: Favorite) = favoriteDao.insert(favorite)
    suspend fun delete(favorite: Favorite) = favoriteDao.delete(favorite)
    suspend fun getFavorite(name: String) = favoriteDao.getFavorite(name)
    suspend fun getAll(): List<Favorite>? = favoriteDao.getAll()
    suspend fun deleteAll() = favoriteDao.deleteAll()
}