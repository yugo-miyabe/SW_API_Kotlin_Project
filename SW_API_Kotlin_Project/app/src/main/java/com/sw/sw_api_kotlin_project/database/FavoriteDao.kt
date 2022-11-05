package com.sw.sw_api_kotlin_project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.FavoriteFilm
import com.sw.sw_api_kotlin_project.model.entity.FavoritePeople
import com.sw.sw_api_kotlin_project.model.entity.FavoritePlanet
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.network.model.Planet

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Insert
    suspend fun insert(people: FavoritePeople)

    @Insert
    suspend fun insert(film: FavoriteFilm)

    @Insert
    suspend fun insert(planet: FavoritePlanet)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Delete
    suspend fun delete(people: FavoritePeople)

    @Delete
    suspend fun delete(film: FavoriteFilm)

    @Delete
    suspend fun delete(planet: FavoritePlanet)

    @Query("SELECT * FROM favorite_table WHERE name = :name LIMIT 1")
    suspend fun getFavorite(name: String): Favorite?

    @Query("SELECT * FROM people_table WHERE people = :people LIMIT 1")
    suspend fun getFavoritePeople(people: People): FavoritePeople?

    @Query("SELECT * FROM film_table WHERE film  = :film  LIMIT 1")
    suspend fun getFavoriteFilm(film: Film): FavoriteFilm?

    @Query("SELECT * FROM planet_table WHERE planet = :planet LIMIT 1")
    suspend fun getFavoritePlanet(planet: Planet): FavoritePlanet?

    /*
    @Query("SELECT * FROM favorite_table,people_table, film_table, planet_table")
    suspend fun getAll(): List<Favorite>?
    */

    @Query("SELECT * FROM favorite_table")
    suspend fun getAll(): List<Favorite>?

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAll()
}