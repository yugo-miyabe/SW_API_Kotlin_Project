package com.sw.sw_api_kotlin_project.data.model.repository

import android.os.Parcelable
import com.sw.sw_api_kotlin_project.data.model.entity.Resource
import com.sw.sw_api_kotlin_project.data.network.model.SWService
import com.sw.sw_api_kotlin_project.data.network.model.Results
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(private val swService: SWService) {
    fun getSearchResult(searchString: String): Flow<Resource<List<Results<out Parcelable>>>> =
        flow {
            emit(Resource.loading(data = null))
            try {
                val searchResponse = swService.getPeopleSearch(searchString)
                val filmsResponse = swService.getFilmsSearch(searchString)
                val planetResponse = swService.getPlanetsSearch(searchString)
                val response: List<Results<out Parcelable>> =
                    listOf(searchResponse, filmsResponse, planetResponse)
                emit(Resource.success(data = response))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "error"))
            }
        }

}
