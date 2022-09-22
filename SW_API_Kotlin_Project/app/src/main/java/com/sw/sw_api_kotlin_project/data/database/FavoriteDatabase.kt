package com.sw.sw_api_kotlin_project.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun FavoriteDao(): FavoriteDao

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