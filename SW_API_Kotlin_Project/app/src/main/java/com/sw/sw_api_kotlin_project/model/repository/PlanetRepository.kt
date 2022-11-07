package com.sw.sw_api_kotlin_project.model.repository

import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.model.entity.Resource
import com.sw.sw_api_kotlin_project.network.SWService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PlanetRepository @Inject constructor(private val swService: SWService) {
    suspend fun getPlanets(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = swService.getPlanets(page)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

    suspend fun getPlanetsSearch(search: String) = swService.getPlanetsSearch(search)
}