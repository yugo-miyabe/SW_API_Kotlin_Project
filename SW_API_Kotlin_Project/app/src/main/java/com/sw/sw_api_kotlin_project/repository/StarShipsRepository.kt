package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.network.SWService

class StarShipsRepository(private val swService: SWService) {
    suspend fun getStarShips(page: Int) = swService.getStarShips(page = page)
}