package com.sw.sw_api_kotlin_project.ui.people_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.repository.StarShipsRepository
import com.sw.sw_api_kotlin_project.utils.ListType
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class PeopleDetailsViewModel(
    private val starShipsRepository: StarShipsRepository,
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {
    private val _favoriteStatus = MutableLiveData<Boolean>()
    val favoriteStatus: LiveData<Boolean> = _favoriteStatus

    fun getStarShips() {
    }

    fun getStarshipsByUrl(starshipUrl: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
        } catch (e: Exception) {
        }
    }

    suspend fun getFavoriteState(name: String) {
        _favoriteStatus.value = checkFavoriteState(name)
    }

    private suspend fun checkFavoriteState(name: String): Boolean = favoriteCheck(name) != null

    suspend fun addOrDeleteFavorite(people: People) {
        val favorite: Favorite? = favoriteCheck(people.name)
        if (favorite == null) {
            insert(Favorite(0, people.name, ListType.PEOPLE, people, null, null))
        } else {
            delete(favorite)
        }
        getFavoriteState(people.name)
    }

    private suspend fun insert(favorite: Favorite) = favoriteRepository.insert(favorite)

    private suspend fun delete(favorite: Favorite) = favoriteRepository.delete(favorite)

    private suspend fun favoriteCheck(name: String): Favorite? =
        favoriteRepository.getFavoriteState(name = name)

}


class PeopleDetailsFactory(
    private val starShipsRepository: StarShipsRepository,
    private val favoriteRepository: FavoriteRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeopleDetailsViewModel(starShipsRepository, favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}