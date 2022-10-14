package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.api.network.SWService
import javax.inject.Inject


class PeopleRepository @Inject constructor(private val swService: SWService) {
    suspend fun getPeople(page: Int) = swService.getPeople(page)
    suspend fun getPeopleSearch(search: String) = swService.getPeopleSearch(search)
}