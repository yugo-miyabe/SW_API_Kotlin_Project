package com.sw.sw_api_kotlin_project.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.FilmsRepository
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.repository.PlanetRepository

class SearchViewModel(
    private val peopleRepository: PeopleRepository,
    private val filmsRepository: FilmsRepository,
    private val planetRepository: PlanetRepository,
) : BaseViewModel() {

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