package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.network.SWService

class PlanetRepository(private val swService: SWService) {
    suspend fun getPlanets(page: Int) = swService.getPlanets(page)
    suspend fun getPlanetsSearch(search: String) = swService.getPlanetsSearch(search)
}