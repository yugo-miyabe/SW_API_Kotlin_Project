package com.sw.sw_api_kotlin_project.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.FilmsRepository
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.repository.PlanetRepository
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class SearchViewModel(
    private val peopleRepository: PeopleRepository,
    private val filmsRepository: FilmsRepository,
    private val planetRepository: PlanetRepository,
) : BaseViewModel() {

    fun getSearchResult(searchString: String) = liveData(Dispatchers.IO) {

        try {
            val searchResponse = peopleRepository.getPeopleSearch(search = searchString)
            //val filmsResponse = filmsRepository.getFilmsSearch(search = searchString)
            //val planetResponse = planetRepository.getPlanetsSearch(search = searchString)
            emit(Resource.success(searchResponse))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

}

class SearchViewModelFactory(
    private val peopleRepository: PeopleRepository,
    private val filmsRepository: FilmsRepository,
    private val planetRepository: PlanetRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(peopleRepository, filmsRepository, planetRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}