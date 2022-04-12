package com.sw.sw_api_kotlin_project.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.fragments.FilmsFragment
import com.sw.sw_api_kotlin_project.fragments.PeopleListFragment
import com.sw.sw_api_kotlin_project.fragments.PlanetsFragment
import com.sw.sw_api_kotlin_project.fragments.SpeciesFragment
import com.sw.sw_api_kotlin_project.fragments.StarShipsFragment
import com.sw.sw_api_kotlin_project.fragments.VehiclesFragment
import com.sw.sw_api_kotlin_project.model.APIRoot
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.utils.Result
import kotlinx.coroutines.launch

class APIRootViewModel(private val apiRepository: APIRepository) : ViewModel() {
    private val _isAPISuccess = MutableLiveData<Boolean>()
    val isAPISuccess = _isAPISuccess
    private val _apiRootURL = MutableLiveData<List<String>>()
    val apiRootURL = _apiRootURL

    fun getAPIRootURL() {
        viewModelScope.launch {
            val api = SWServiceClient.getService()
            val response = api.apiRoot()
            when (val result = apiRepository.getResponse(response)) {
                is Result.Success -> {
                    _apiRootURL.value = getUrlList(result.data)
                    _isAPISuccess.value = true
                }
                is Result.Error -> {
                    result.type
                    _isAPISuccess.value = false
                }
                else -> {
                    // 何もしない
                }
            }
        }
    }

    fun getFragmentList(): Map<String, Fragment> {
        return mapOf(
            "people" to PeopleListFragment(),
            "planets" to PlanetsFragment(),
            "films" to FilmsFragment(),
            "species" to SpeciesFragment(),
            "vehicles" to VehiclesFragment(),
            "starships" to StarShipsFragment()
        )
    }

    // TODO 要修正
    private fun getUrlList(apiRootURL: APIRoot): List<String> {
        return listOf(
            apiRootURL.peopleUrl,
            apiRootURL.planetsUrl,
            apiRootURL.filmsUrl,
            apiRootURL.speciesUrl,
            apiRootURL.vehiclesUrl,
            apiRootURL.starshipsUrl
        )
    }

}

class APIRootViewModelFactory(private val apiRepository: APIRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(APIRootViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return APIRootViewModel(apiRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}