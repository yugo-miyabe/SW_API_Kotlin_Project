package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.api.network.SWService
import javax.inject.Inject

class FilmsRepository @Inject constructor(private val swService: SWService) {
    suspend fun getFilms(page: Int) = swService.getFilms(page)
    suspend fun getFilmsSearch(search: String) = swService.getFilmsSearch(search)
}