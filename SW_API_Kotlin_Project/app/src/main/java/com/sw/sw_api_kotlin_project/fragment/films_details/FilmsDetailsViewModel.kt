package com.sw.sw_api_kotlin_project.fragment.films_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.model.Film
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.utils.DateFormatter
import com.sw.sw_api_kotlin_project.utils.ListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsDetailsViewModel @Inject constructor(
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
            val favorite: Favorite? = favoriteRepository.getFavoriteState(film.title)
            if (favorite == null) {
                favoriteRepository.insert(
                    Favorite(
                        id = 0,
                        name = film.title,
                        listType = ListType.FILM,
                        people = null,
                        film = film,
                        planet = null,
                        registrationDate = DateFormatter.getTodayDateStringYYYYMMDDHHMMSS()
                    )
                )
            } else {
                favoriteRepository.delete(favorite)
            }
            getFavoriteState(film.title)
        }
    }

    private suspend fun checkFavoriteState(name: String): Boolean =
        favoriteRepository.getFavoriteState(name) != null
}
