package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.database.FavoriteDao

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    suspend fun insert(favorite: Favorite) = favoriteDao.insert(favorite = favorite)

    suspend fun delete(favorite: Favorite) = favoriteDao.delete(favorite = favorite)

    suspend fun getFavoriteState(name: String) = favoriteDao.getFavorite(name = name)

    /**
     * 全て取得
     */
    suspend fun getAll(): List<Favorite>? = favoriteDao.getAll()

    /**
     * 全て削除
     */
    suspend fun deleteAll() = favoriteDao.deleteAll()
}