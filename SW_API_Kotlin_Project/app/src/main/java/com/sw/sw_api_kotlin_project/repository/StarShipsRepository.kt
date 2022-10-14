package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.api.network.SWService

// TODO 宇宙船一覧作成時に使用
class StarShipsRepository(private val swService: SWService) {
    suspend fun getStarShips(page: Int) = swService.getStarShips(page)
}