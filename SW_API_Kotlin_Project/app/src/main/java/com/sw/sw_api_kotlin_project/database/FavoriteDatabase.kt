package com.sw.sw_api_kotlin_project.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.FavoriteFilm
import com.sw.sw_api_kotlin_project.model.entity.FavoritePeople
import com.sw.sw_api_kotlin_project.model.entity.FavoritePlanet

@Database(
    entities = [Favorite::class, FavoritePeople::class, FavoriteFilm::class, FavoritePlanet::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}