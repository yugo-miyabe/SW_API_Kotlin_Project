package com.sw.sw_api_kotlin_project.model.repository

import com.sw.sw_api_kotlin_project.model.entity.Resource
import com.sw.sw_api_kotlin_project.network.SWService
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.Results
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmRepository @Inject constructor(private val swService: SWService) {
    suspend fun getFilm(page: Int): Flow<Resource<Results<Film>>> = flow {
        emit(Resource.loading(data = null))
        try {
            val response = swService.getFilms(page)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

    suspend fun getFilmsSearch(search: String) = swService.getFilmsSearch(search)
}