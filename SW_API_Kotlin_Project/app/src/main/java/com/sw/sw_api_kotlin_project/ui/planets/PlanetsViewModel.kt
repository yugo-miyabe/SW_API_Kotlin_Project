package com.sw.sw_api_kotlin_project.ui.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.PlanetsRepository
import com.sw.sw_api_kotlin_project.utils.PageType
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class PlanetsViewModel(private val planetsRepository: PlanetsRepository) : BaseViewModel() {

    fun getPlanets(pageType: PageType) = liveData(Dispatchers.IO) {
        pageParameterFormat(pageType)
        emit(Resource.loading(data = null))
        try {
            val response = planetsRepository.getPlanets(page)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }
}

class PlanetsViewModelFactory(private val planetsRepository: PlanetsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanetsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlanetsViewModel(planetsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}