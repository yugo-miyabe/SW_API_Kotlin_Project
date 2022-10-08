package com.sw.sw_api_kotlin_project.ui.planet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.PlanetRepository
import com.sw.sw_api_kotlin_project.utils.PageType
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class PlanetViewModel(private val planetRepository: PlanetRepository) : BaseViewModel() {

    fun getPlanets(pageType: PageType) = liveData(Dispatchers.IO) {
        pageParameterFormat(pageType)
        emit(Resource.loading(null))
        try {
            val response = planetRepository.getPlanets(page)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }
}

class PlanetViewModelFactory(private val planetRepository: PlanetRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlanetViewModel(planetRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}