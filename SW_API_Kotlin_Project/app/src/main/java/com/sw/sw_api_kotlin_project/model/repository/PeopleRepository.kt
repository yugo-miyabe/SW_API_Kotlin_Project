package com.sw.sw_api_kotlin_project.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sw.sw_api_kotlin_project.model.PeoplePagingSource
import com.sw.sw_api_kotlin_project.model.entity.Resource
import com.sw.sw_api_kotlin_project.network.SWService
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.network.model.Results
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

    fun getPeopleStream(): Flow<PagingData<People>> {
        val pageSize = 82
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PeoplePagingSource(swService) }
        ).flow
    }

    suspend fun getPeopleSearch(search: String) = swService.getPeopleSearch(search)
}