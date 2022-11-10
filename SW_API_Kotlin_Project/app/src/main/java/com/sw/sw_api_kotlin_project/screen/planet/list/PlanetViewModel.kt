package com.sw.sw_api_kotlin_project.screen.planet.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.model.entity.RequestStatus
import com.sw.sw_api_kotlin_project.model.repository.PlanetRepository
import com.sw.sw_api_kotlin_project.network.model.Planet
import com.sw.sw_api_kotlin_project.network.model.Results
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(
    private val planetRepository: PlanetRepository
) : BaseViewModel() {
    private val _planetList = MutableLiveData<Results<Planet>>()
    val planetList: LiveData<Results<Planet>> get() = _planetList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _failureMessage = MutableLiveData<String>()
    val failureMessage: LiveData<String> get() = _failureMessage


    fun getPlanets(pageType: PageType) {
        pageParameterFormat(pageType)
        viewModelScope.launch {
            planetRepository.getPlanets(page).collect { response ->
                when (response.status) {
                    RequestStatus.SUCCESS -> {
                        _isLoading.value = false
                        _planetList.value = response.data!!
                    }
                    RequestStatus.ERROR -> {
                        _isLoading.value = false
                        _failureMessage.value = response.message!!
                    }
                    RequestStatus.LOADING -> {
                        _isLoading.value = true
                        _failureMessage.value = ""
                    }
                }
            }
        }
    }

}
