package com.sw.sw_api_kotlin_project.screen.planet.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.model.entity.SWLiveDataObserver
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

    fun getPlanets(viewLifecycleOwner: LifecycleOwner, pageType: PageType) {
        pageParameterFormat(pageType)
        val planetObserver = object : SWLiveDataObserver<Results<Planet>>() {
            override fun onSuccess(data: Results<Planet>?) {
                _planetList.value = data!!
                _isLoading.value = false
            }

            override fun onError(errorMessage: String) {
                _isLoading.value = false
                _failureMessage.value = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                _isLoading.value = true
                _failureMessage.value = ""
            }
        }

        viewModelScope.launch {
            planetRepository.getPlanets(page).observe(viewLifecycleOwner, planetObserver)
        }
    }
}
