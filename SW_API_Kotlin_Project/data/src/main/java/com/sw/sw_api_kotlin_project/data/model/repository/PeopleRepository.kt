package com.sw.sw_api_kotlin_project.data.model.repository

import com.sw.sw_api_kotlin_project.data.model.data.PeoplePagingSource
import com.sw.sw_api_kotlin_project.data.model.entity.Resource
import com.sw.sw_api_kotlin_project.data.network.model.SWService
import com.sw.sw_api_kotlin_project.data.network.model.People
import com.sw.sw_api_kotlin_project.data.network.model.Results
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val swService: SWService) {
    suspend fun getPeople(page: Int): Flow<Resource<Results<People>>> = flow {
        emit(Resource.loading(data = null))
        try {
            val response = swService.getPeople(page)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

    fun peopleListPagingSource() = PeoplePagingSource(swService)

    suspend fun getPeopleSearch(search: String) = swService.getPeopleSearch(search)
}