package com.sw.sw_api_kotlin_project.ui.planet_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.utils.DateUtils
import com.sw.sw_api_kotlin_project.utils.ListType
import kotlinx.coroutines.launch

class PlanetDetailsViewModel(private val favoriteRepository: FavoriteRepository) : BaseViewModel() {
    private val _favoriteStatus = MutableLiveData<Boolean>()
    val favoriteStatus: LiveData<Boolean> = _favoriteStatus

    fun getFavoriteState(name: String) {
        viewModelScope.launch {
            _favoriteStatus.value = checkFavoriteState(name)
        }
    }

    fun addOrDeleteFavorite(planet: Planet) {
        viewModelScope.launch {
            val favorite: Favorite? = isFavoriteExist(planet.name)
            if (favorite == null) {
                insert(
                    Favorite(
                        id = 0,
                        name = planet.name,
                        listType = ListType.PLANETS,
                        people = null,
                        film = null,
                        planet = planet,
                        registrationDate = DateUtils.getTodayDateStringYYYYMMDDHHMMSS()
                    )
                )
            } else {
                delete(favorite)
            }
            getFavoriteState(planet.name)
        }
    }

    private suspend fun insert(favorite: Favorite) = favoriteRepository.insert(favorite)

    private suspend fun delete(favorite: Favorite) = favoriteRepository.delete(favorite)

    private suspend fun checkFavoriteState(name: String): Boolean = isFavoriteExist(name) != null

    private suspend fun isFavoriteExist(name: String): Favorite? =
        favoriteRepository.getFavoriteState(name = name)

}

class PlanetDetailsFactory(
    private val favoriteRepository: FavoriteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanetDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlanetDetailsViewModel(favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}