package com.sw.sw_api_kotlin_project.model.repository

import com.sw.sw_api_kotlin_project.network.SWService
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val swService: SWService) {
    suspend fun getPeople(page: Int) = swService.getPeople(page)
    suspend fun getPeopleSearch(search: String) = swService.getPeopleSearch(search)
}