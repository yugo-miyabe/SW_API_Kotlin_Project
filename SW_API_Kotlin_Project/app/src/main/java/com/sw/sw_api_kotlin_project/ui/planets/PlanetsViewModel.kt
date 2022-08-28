package com.sw.sw_api_kotlin_project.ui.planets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.utils.Result
import kotlinx.coroutines.launch

class PlanetsViewModel(private val apiRepository: APIRepository) : BaseViewModel() {
    private val _planet = MutableLiveData<Results<Planet>?>()
    val planet = _planet

    fun fetchPlanets(page: Int = 1) {
        startLoading()
        viewModelScope.launch {
            val api = SWServiceClient.getService()
            val response = api.getPlanets(page)
            when (val result = apiRepository.fetchResponse(response)) {
                is Result.Success -> {
                    _planet.value = result.data
                    stopLoading()
                }
                is Result.Error -> {
                    // TODO エラーハンドリング
                    result.type
                    stopLoading()
                }
                else -> {
                    // ここには来ない
                }
            }
        }
    }
}

class PlanetsViewModelFactory(private val apiRepository: APIRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanetsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlanetsViewModel(apiRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}