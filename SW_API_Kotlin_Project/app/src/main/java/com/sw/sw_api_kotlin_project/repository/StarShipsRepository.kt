package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.data.model.Starships
import com.sw.sw_api_kotlin_project.network.SWService

class StarShipsRepository(private val swService: SWService) {
    suspend fun getStarShips(page: Int) = swService.getStarShips(page = page)

    private val starshipMap = mutableMapOf<String, Starships>()

    
}