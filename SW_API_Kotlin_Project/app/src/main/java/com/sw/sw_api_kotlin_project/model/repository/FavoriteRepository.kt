package com.sw.sw_api_kotlin_project.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.database.FavoriteDao
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.FavoriteFilm
import com.sw.sw_api_kotlin_project.model.entity.FavoritePeople
import com.sw.sw_api_kotlin_project.model.entity.FavoritePlanet
import com.sw.sw_api_kotlin_project.model.entity.Resource
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.network.model.Planet
import com.sw.sw_api_kotlin_project.utils.DateFormatter
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {
    suspend fun add(people: People) {
        favoriteDao.insert(
            FavoritePeople(
                people = people,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun add(film: Film) {
        favoriteDao.insert(
            FavoriteFilm(
                film = film,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun add(planet: Planet) {
        favoriteDao.insert(
            FavoritePlanet(
                planet = planet,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun delete(people: People) {
        favoriteDao.delete(
            FavoritePeople(
                people = people,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun delete(film: Film) {
        favoriteDao.delete(
            FavoriteFilm(
                film = film,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun delete(planet: Planet) {
        favoriteDao.delete(
            FavoritePlanet(
                planet = planet,
                registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
            )
        )
    }

    suspend fun getFavorite(people: People): Boolean {
        return favoriteDao.getFavoritePeople(people) == null
    }

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