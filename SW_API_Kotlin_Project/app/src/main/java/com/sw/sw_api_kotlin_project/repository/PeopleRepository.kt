package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.data.database.FavoriteDao
import com.sw.sw_api_kotlin_project.network.SWService


class PeopleRepository(private val swService: SWService, private val favoriteDao: FavoriteDao) {

    suspend fun getPeople(page: Int) = swService.getPeople(page)

    // TODO Peopleのみに修正
    fun getFavorite() = favoriteDao.getAll()
}