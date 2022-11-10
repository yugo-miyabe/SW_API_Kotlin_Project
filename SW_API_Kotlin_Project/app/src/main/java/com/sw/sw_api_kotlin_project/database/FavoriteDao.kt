package com.sw.sw_api_kotlin_project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite_table WHERE name = :name LIMIT 1")
    suspend fun get(name: String): Favorite?

    @Query("SELECT * FROM favorite_table")
    suspend fun getAll(): List<Favorite>?

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM favorite_table WHERE name = :name LIMIT 1")
    fun getFlow(name: String): Flow<Favorite>?

    @Query("SELECT * FROM favorite_table")
    fun getAllFlow(): Flow<List<Favorite>?>

}