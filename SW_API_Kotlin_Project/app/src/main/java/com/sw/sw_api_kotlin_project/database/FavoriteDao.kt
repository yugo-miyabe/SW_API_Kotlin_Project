package com.sw.sw_api_kotlin_project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.FavoriteFilm
import com.sw.sw_api_kotlin_project.model.entity.FavoritePeople
import com.sw.sw_api_kotlin_project.model.entity.FavoritePlanet

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Insert
    suspend fun insertPeople(people: FavoritePeople)

    @Insert
    suspend fun insertFilm(film: FavoriteFilm)

    @Insert
    suspend fun insertPlanet(planet: FavoritePlanet)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Delete
    suspend fun deletePeople(people: FavoritePeople)

    @Delete
    suspend fun deleteFilm(film: FavoriteFilm)

    @Delete
    suspend fun deletePlanet(planet: FavoritePlanet)

    @Query("SELECT * FROM favorite_table WHERE name = :name LIMIT 1")
    suspend fun getFavorite(name: String): Favorite?

    @Query("SELECT * FROM favorite_table")
    suspend fun getAll(): List<Favorite>?

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAll()
}