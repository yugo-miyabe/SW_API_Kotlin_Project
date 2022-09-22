package com.sw.sw_api_kotlin_project.data.dao

import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.network.SWService

class PeopleSWAPIDao(private val swService: SWService) {

    suspend fun fetchPeople(page: Int): Results<People> {
        return swService.getPeople(page)
    }

    suspend fun searchPeople(page: Int, searchString: String): Results<People> {
        return swService.getPeopleSearchPage(searchString, page)
    }

    suspend fun fetchPeopleByUrl(peopleUrl: String): People {
        return swService.getPeopleByUrl(peopleUrl)
    }

}