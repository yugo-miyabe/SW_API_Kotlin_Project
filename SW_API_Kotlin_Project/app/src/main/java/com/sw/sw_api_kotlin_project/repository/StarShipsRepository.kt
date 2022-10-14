package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.api.network.SWService
import javax.inject.Inject

// TODO 宇宙船一覧作成時に使用
class StarShipsRepository @Inject constructor(private val swService: SWService) {
    suspend fun getStarShips(page: Int) = swService.getStarShips(page)
}