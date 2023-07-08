package com.sw.sw_api_kotlin_project.data.model.repository

import com.sw.sw_api_kotlin_project.data.model.data.FilmPagingSource
import com.sw.sw_api_kotlin_project.data.model.entity.Resource
import com.sw.sw_api_kotlin_project.data.network.model.SWService
import com.sw.sw_api_kotlin_project.data.network.model.Film
import com.sw.sw_api_kotlin_project.data.network.model.Results
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

    fun filmListPagingSource() = FilmPagingSource(swService = swService)

    suspend fun getFilmsSearch(search: String) = swService.getFilmsSearch(search)
}