package com.sw.sw_api_kotlin_project.screen.planet.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sw.sw_api_kotlin_project.data.model.repository.PlanetRepository
import com.sw.sw_api_kotlin_project.data.network.model.Planet
import com.sw.sw_api_kotlin_project.data.network.model.Results
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import com.sw.sw_api_kotlin_project.screen.planet.list.PlanetListFragmentDirections.Companion.actionPlanetToPlanetDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    val planetItems: Flow<PagingData<Planet>> = Pager(config = PagingConfig(
        pageSize = 1, enablePlaceholders = false
    ), pagingSourceFactory = {
        planetRepository.planetListPagingSource()
    }).flow.cachedIn(viewModelScope)

    fun onTapPlanet(planet: Planet) {
        addNavigationEvent(actionPlanetToPlanetDetail(planet = planet))
    }

}
