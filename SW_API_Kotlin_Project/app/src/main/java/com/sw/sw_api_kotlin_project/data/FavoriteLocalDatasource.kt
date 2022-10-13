package com.sw.sw_api_kotlin_project.data

import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.database.FavoriteDao
import javax.inject.Inject

class FavoriteLocalDatasource @Inject constructor(private val favoriteDao: FavoriteDao) :
    FavoriteDatasource {

    override suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }

    override suspend fun isFavoriteExist(name: String): Favorite? =
        return favoriteDao.getFavorite(name)

}