package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.data.database.FavoriteDao

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    // TODO Peopleのみに修正
    fun getPeople() = favoriteDao.getAll()

    /**
     * 全て取得
     */
    fun getAll() = favoriteDao.getAll()
}