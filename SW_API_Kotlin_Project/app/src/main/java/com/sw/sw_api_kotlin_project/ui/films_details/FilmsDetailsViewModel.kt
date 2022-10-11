package com.sw.sw_api_kotlin_project.ui.films_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.model.Film
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.utils.DateUtils
import com.sw.sw_api_kotlin_project.utils.ListType
import kotlinx.coroutines.launch

class FilmsDetailsViewModel(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {
    private val _favoriteStatus = MutableLiveData<Boolean>()
    val favoriteStatus: LiveData<Boolean> = _favoriteStatus

    fun getFavoriteState(name: String) {
        viewModelScope.launch {
            _favoriteStatus.value = checkFavoriteState(name)
        }
    }

    fun addOrDeleteFavorite(film: Film) {
        viewModelScope.launch {
            val favorite: Favorite? = favoriteCheck(film.title)
            if (favorite == null) {
                insert(
                    Favorite(
                        id = 0,
                        name = film.title,
                        listType = ListType.FILM,
                        people = null,
                        film = film,
                        planet = null,
                        registrationDate = DateUtils.getTodayDateStringYYYYMMDDHHMMSS()
                    )
                )
            } else {
                delete(favorite)
            }
            getFavoriteState(film.title)
        }
    }

    private suspend fun insert(favorite: Favorite) = favoriteRepository.insert(favorite)

    private suspend fun delete(favorite: Favorite) = favoriteRepository.delete(favorite)

    private suspend fun checkFavoriteState(name: String): Boolean = favoriteCheck(name) != null

    private suspend fun favoriteCheck(name: String): Favorite? =
        favoriteRepository.getFavoriteState(name)
}


class FilmsDetailsFactory(private val favoriteRepository: FavoriteRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmsDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilmsDetailsViewModel(favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}