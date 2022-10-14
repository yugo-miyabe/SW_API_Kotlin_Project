package com.sw.sw_api_kotlin_project.fragment.search

import android.os.Parcelable
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.repository.FilmsRepository
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.repository.PlanetRepository
import com.sw.sw_api_kotlin_project.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val filmsRepository: FilmsRepository,
    private val planetRepository: PlanetRepository,
) : BaseViewModel() {

    fun getSearchResult(searchString: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val searchResponse = peopleRepository.getPeopleSearch(search = searchString)
            val filmsResponse = filmsRepository.getFilmsSearch(search = searchString)
            val planetResponse = planetRepository.getPlanetsSearch(search = searchString)
            val response: List<Results<out Parcelable>> =
                listOf(searchResponse, filmsResponse, planetResponse)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

}
