package com.sw.sw_api_kotlin_project.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.database.FavoriteDao
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.Resource
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {
    suspend fun add(favorite: Favorite) = favoriteDao.insert(favorite)
    suspend fun delete(favorite: Favorite) = favoriteDao.delete(favorite)
    suspend fun getFavorite(name: String) = favoriteDao.getFavorite(name)

    fun getAll(): LiveData<Resource<List<Favorite>?>> = liveData {
        emit(Resource.loading(null))
        try {
            val response = favoriteDao.getAll()
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

    suspend fun deleteAll() = favoriteDao.deleteAll()
}