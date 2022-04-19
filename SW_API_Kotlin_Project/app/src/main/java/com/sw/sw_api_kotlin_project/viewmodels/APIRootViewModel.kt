package com.sw.sw_api_kotlin_project.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.fragments.FilmsFragment
import com.sw.sw_api_kotlin_project.fragments.PeopleListFragment
import com.sw.sw_api_kotlin_project.fragments.PlanetsFragment
import com.sw.sw_api_kotlin_project.fragments.SpeciesFragment
import com.sw.sw_api_kotlin_project.fragments.StarShipsFragment
import com.sw.sw_api_kotlin_project.fragments.VehiclesFragment
import com.sw.sw_api_kotlin_project.model.root.APIRoot
import com.sw.sw_api_kotlin_project.model.root.HomeData
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.utils.Result
import kotlinx.coroutines.launch

class APIRootViewModel(private val apiRepository: APIRepository) : BaseViewModel() {
    private val _homeData = MutableLiveData<List<HomeData>>()
    val homeData = _homeData

    fun getAPIRootURL() {
        viewModelScope.launch {
            val api = SWServiceClient.getService()
            val response = api.apiRoot()
            when (val result = apiRepository.getResponse(response)) {
                is Result.Success -> {
                    homeData.value = getHomeDataList(result.data)
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

    // TODO 要修正
    private fun getHomeDataList(apiRootURL: APIRoot): List<HomeData> {
        return listOf(
            HomeData("登場人物", apiRootURL.peopleUrl, PeopleListFragment()),
            HomeData("惑星", apiRootURL.planetsUrl, PlanetsFragment()),
            HomeData("映画", apiRootURL.filmsUrl, FilmsFragment()),
            HomeData("人種", apiRootURL.speciesUrl, SpeciesFragment()),
            HomeData("自動車", apiRootURL.vehiclesUrl, VehiclesFragment()),
            HomeData("宇宙船", apiRootURL.starshipsUrl, StarShipsFragment()),
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