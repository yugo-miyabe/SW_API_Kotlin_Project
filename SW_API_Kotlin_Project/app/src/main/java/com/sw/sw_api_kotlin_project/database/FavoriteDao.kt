package com.sw.sw_api_kotlin_project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sw.sw_api_kotlin_project.model.entity.Favorite

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite_table WHERE name = :name LIMIT 1")
    suspend fun getFavorite(name: String): Favorite?

    @Query("SELECT * FROM favorite_table")
    suspend fun getFavoriteAll(): List<Favorite>?

    @Query("DELETE FROM favorite_table")
    suspend fun deleteFavoriteAll()
}