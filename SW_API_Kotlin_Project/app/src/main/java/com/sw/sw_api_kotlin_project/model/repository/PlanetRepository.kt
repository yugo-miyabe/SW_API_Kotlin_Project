package com.sw.sw_api_kotlin_project.model.repository

import com.sw.sw_api_kotlin_project.network.SWService
import javax.inject.Inject

class PlanetRepository @Inject constructor(private val swService: SWService) {
    suspend fun getPlanets(page: Int) = swService.getPlanets(page)
    suspend fun getPlanetsSearch(search: String) = swService.getPlanetsSearch(search)
}