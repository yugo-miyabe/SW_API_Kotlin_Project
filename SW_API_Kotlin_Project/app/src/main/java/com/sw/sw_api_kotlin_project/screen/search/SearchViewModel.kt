package com.sw.sw_api_kotlin_project.screen.search

import android.os.Parcelable
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import com.sw.sw_api_kotlin_project.network.model.Results
import com.sw.sw_api_kotlin_project.model.repository.FilmRepository
import com.sw.sw_api_kotlin_project.model.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.model.repository.PlanetRepository
import com.sw.sw_api_kotlin_project.model.entity.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val filmRepository: FilmRepository,
    private val planetRepository: PlanetRepository,
) : BaseViewModel() {

    fun getSearchResult(searchString: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val searchResponse = peopleRepository.getPeopleSearch(searchString)
            val filmsResponse = filmRepository.getFilmsSearch(searchString)
            val planetResponse = planetRepository.getPlanetsSearch(searchString)
            val response: List<Results<out Parcelable>> =
                listOf(searchResponse, filmsResponse, planetResponse)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

}
