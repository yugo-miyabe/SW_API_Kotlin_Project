package com.sw.sw_api_kotlin_project.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("DELETE FROM favorite_table")
    suspend fun clear()

    @Query("SELECT * FROM favorite_table WHERE name = :name LIMIT 1")
    suspend fun getFavorite(name: String): Favorite?

    /**
     * 全て取得
     */
    @Query("SELECT * FROM favorite_table")
    suspend fun getAll(): List<Favorite>?
}