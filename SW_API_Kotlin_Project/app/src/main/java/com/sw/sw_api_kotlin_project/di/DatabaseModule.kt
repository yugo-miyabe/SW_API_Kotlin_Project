package com.sw.sw_api_kotlin_project.di

import android.content.Context
import androidx.room.Room
import com.sw.sw_api_kotlin_project.data.database.FavoriteDao
import com.sw.sw_api_kotlin_project.data.database.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteDatabase::class.java,
            "favorite",
        ).build()
    }

    @Provides
    fun provideFavoriteDao(database: FavoriteDatabase): FavoriteDao {
        return database.favoriteDao()
    }


}