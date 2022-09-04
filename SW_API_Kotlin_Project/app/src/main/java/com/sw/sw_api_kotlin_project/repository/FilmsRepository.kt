package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.network.SWService

class FilmsRepository(private val swService: SWService) {
    suspend fun getFilms(page: Int) = swService.getFilms(page)
}