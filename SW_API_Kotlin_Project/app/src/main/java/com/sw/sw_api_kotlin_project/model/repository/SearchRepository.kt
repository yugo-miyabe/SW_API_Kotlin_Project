package com.sw.sw_api_kotlin_project.model.repository

import android.os.Parcelable
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.model.entity.Resource
import com.sw.sw_api_kotlin_project.network.SWService
import com.sw.sw_api_kotlin_project.network.model.Results
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SearchRepository @Inject constructor(private val swService: SWService) {
    fun getSearchResult(searchString: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val searchResponse = swService.getPeopleSearch(searchString)
            val filmsResponse = swService.getFilmsSearch(searchString)
            val planetResponse = swService.getPlanetsSearch(searchString)
            val response: List<Results<out Parcelable>> =
                listOf(searchResponse, filmsResponse, planetResponse)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

}