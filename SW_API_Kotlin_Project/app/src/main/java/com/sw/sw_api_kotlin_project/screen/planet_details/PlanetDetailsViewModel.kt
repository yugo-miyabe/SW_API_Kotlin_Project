package com.sw.sw_api_kotlin_project.screen.planet_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.utils.DateFormatter
import com.sw.sw_api_kotlin_project.utils.ListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailsViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {
    private val _favoriteStatus = MutableLiveData<Boolean>()
    val favoriteStatus: LiveData<Boolean> = _favoriteStatus

    fun getFavoriteState(name: String) {
        viewModelScope.launch {
            _favoriteStatus.value = checkFavoriteState(name)
        }
    }

    fun addOrDeleteFavorite(planet: Planet) {
        viewModelScope.launch {
            val favorite: Favorite? = favoriteRepository.getFavoriteState(planet.name)
            if (favorite == null) {
                favoriteRepository.insert(
                    Favorite(
                        id = 0,
                        name = planet.name,
                        listType = ListType.PLANETS,
                        people = null,
                        film = null,
                        planet = planet,
                        registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
                    )
                )
            } else {
                favoriteRepository.delete(favorite)
            }
            getFavoriteState(planet.name)
        }
    }

    private suspend fun checkFavoriteState(name: String): Boolean =
        favoriteRepository.getFavoriteState(name) != null

}