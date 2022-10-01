package com.sw.sw_api_kotlin_project.ui.planet_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.repository.PlanetRepository
import java.util.*

class PlanetDetailsViewModel(private val favoriteRepository: FavoriteRepository) : BaseViewModel() {
    private val _favoriteStatus = MutableLiveData<Boolean>()
    val favoriteStatus: LiveData<Boolean> = _favoriteStatus

    suspend fun getFavoriteState(name: String) {
        _favoriteStatus.value = checkFavoriteState(name)
    }

    suspend fun addOrDeleteFavorite(name: String) {
        val favorite: Favorite? = favoriteCheck(name)
        if (favorite == null) {
            //insert(Favorite(0, name, Date()))
        } else {
            delete(favorite)
        }
        getFavoriteState(name)
    }

    private suspend fun checkFavoriteState(name: String): Boolean = favoriteCheck(name) != null

    private suspend fun insert(favorite: Favorite) = favoriteRepository.insert(favorite)

    private suspend fun delete(favorite: Favorite) = favoriteRepository.delete(favorite)

    private suspend fun favoriteCheck(name: String): Favorite? =
        favoriteRepository.getFavoriteState(name = name)

}

class PlanetDetailsFactory(
    private val planetRepository: PlanetRepository,
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