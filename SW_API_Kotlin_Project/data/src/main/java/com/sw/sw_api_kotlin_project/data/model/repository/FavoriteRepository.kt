package com.sw.sw_api_kotlin_project.data.model.repository

import com.sw.sw_api_kotlin_project.data.database.FavoriteDao
import com.sw.sw_api_kotlin_project.data.model.entity.Favorite
import com.sw.sw_api_kotlin_project.data.model.entity.ListType
import com.sw.sw_api_kotlin_project.data.network.model.Film
import com.sw.sw_api_kotlin_project.data.network.model.People
import com.sw.sw_api_kotlin_project.data.network.model.Planet
import com.sw.sw_api_kotlin_project.data.utils.DateFormatter
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
