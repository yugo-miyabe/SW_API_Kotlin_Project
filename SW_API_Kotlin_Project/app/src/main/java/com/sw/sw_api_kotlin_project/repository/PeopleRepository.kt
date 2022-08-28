package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.network.SWService


class PeopleRepository(private val swService: SWService) {

    suspend fun getPeople(page: Int) = swService.getPeople(page)
}