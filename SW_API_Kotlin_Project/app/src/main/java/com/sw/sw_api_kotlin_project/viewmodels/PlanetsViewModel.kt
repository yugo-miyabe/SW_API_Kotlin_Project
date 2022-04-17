package com.sw.sw_api_kotlin_project.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.model.planet.PlanetRoot
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.utils.Result
import kotlinx.coroutines.launch

class PlanetsViewModel(private val apiRepository: APIRepository) : BaseViewModel() {
    private val _planet = MutableLiveData<PlanetRoot>()
    val planet = _planet

    fun getPlanetsAPI() {
        viewModelScope.launch {
            val api = SWServiceClient.getService()
            val response = api.planets()
            when (val result = apiRepository.getResponse(response)) {
                is Result.Success -> {
                    _planet.value = result.data
                }
                is Result.Error -> {
                    // TODO エラーハンドリング
                    result.type
                }
                else -> {
                    // 何もしない
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