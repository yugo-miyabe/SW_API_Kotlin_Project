package com.sw.sw_api_kotlin_project.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.model.starships.StarshipsRoot
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.utils.Result
import kotlinx.coroutines.launch

class StarShipsViewModel(private val apiRepository: APIRepository) : BaseViewModel() {
    private val _starShips = MutableLiveData<StarshipsRoot>()
    val starShips = _starShips

    fun getStarShipsAPI() {
        viewModelScope.launch {
            val api = SWServiceClient.getService()
            val response = api.starships()
            when (val result = apiRepository.getResponse(response)) {
                is Result.Success -> {
                    starShips.value = result.data
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

class StarShipsViewModelFactory(private val apiRepository: APIRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StarShipsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StarShipsViewModel(apiRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}