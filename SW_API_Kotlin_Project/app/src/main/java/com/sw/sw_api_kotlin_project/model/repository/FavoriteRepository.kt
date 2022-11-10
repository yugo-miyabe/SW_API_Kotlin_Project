package com.sw.sw_api_kotlin_project.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.database.FavoriteDao
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.ListType
import com.sw.sw_api_kotlin_project.model.entity.Resource
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.network.model.Planet
import com.sw.sw_api_kotlin_project.utils.DateFormatter
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {

    suspend fun add(people: People) {
        favoriteDao.insert(
            Favorite(
                name = people.name,
                listType = ListType.PEOPLE,
                people = people,
                film = null,
                planet = null,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun add(film: Film) {
        favoriteDao.insert(
            Favorite(
                name = film.title,
                listType = ListType.FILM,
                people = null,
                film = film,
                planet = null,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun add(planet: Planet) {
        favoriteDao.insert(
            Favorite(
                name = planet.name,
                listType = ListType.PLANETS,
                people = null,
                film = null,
                planet = planet,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun delete(favorite: Favorite) = favoriteDao.delete(favorite)

    suspend fun get(name: String) = favoriteDao.get(name)

    suspend fun getAll(): LiveData<Resource<List<Favorite>?>> = liveData(Dispatchers.IO) {
        try {
            val response = favoriteDao.getAll()
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

    suspend fun deleteAll() = favoriteDao.deleteAll()

    fun getFlow(name: String) = favoriteDao.getFlow(name)

    fun getAllFlow() = favoriteDao.getAllFlow()
}