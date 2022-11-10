package com.sw.sw_api_kotlin_project.model.repository

import com.sw.sw_api_kotlin_project.database.FavoriteDao
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.ListType
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.network.model.Planet
import com.sw.sw_api_kotlin_project.utils.DateFormatter
import kotlinx.coroutines.flow.Flow
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

    fun get(name: String): Flow<Favorite?> = favoriteDao.get(name)

    suspend fun delete(favorite: Favorite) = favoriteDao.delete(favorite)

    val favoriteList: Flow<List<Favorite>?> get() = favoriteDao.getAll()

    suspend fun deleteAll() = favoriteDao.deleteAll()

}