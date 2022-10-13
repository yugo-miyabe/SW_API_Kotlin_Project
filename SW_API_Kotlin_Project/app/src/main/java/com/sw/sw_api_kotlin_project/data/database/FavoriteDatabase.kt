package com.sw.sw_api_kotlin_project.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private var instance: FavoriteDatabase? = null

        fun getDatabase(context: Context): FavoriteDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorite"
                ).build()
            }
            return instance!!
        }
    }

}