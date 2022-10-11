package com.sw.sw_api_kotlin_project.ui.people_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.utils.DateUtils
import com.sw.sw_api_kotlin_project.utils.ListType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleDetailsViewModel(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {
    private val _favoriteStatus = MutableLiveData<Boolean>()
    val favoriteStatus: LiveData<Boolean> = _favoriteStatus

    fun getFavoriteState(name: String) {
        viewModelScope.launch {
            _favoriteStatus.value = checkFavoriteState(name)
        }
    }

    fun addOrDeleteFavorite(people: People) {
        viewModelScope.launch(Dispatchers.IO) {
            val favorite: Favorite? = favoriteCheck(people.name)
            if (favorite == null) {
                insert(
                    Favorite(
                        id = 0,
                        name = people.name,
                        listType = ListType.PEOPLE,
                        people = people,
                        film = null,
                        planet = null,
                        registrationDate = DateUtils.getTodayDateStringYYYYMMDDHHMMSS()
                    )
                )
            } else {
                delete(favorite)
            }
            getFavoriteState(people.name)
        }
    }

    private suspend fun insert(favorite: Favorite) = favoriteRepository.insert(favorite)

    private suspend fun delete(favorite: Favorite) = favoriteRepository.delete(favorite)

    private suspend fun checkFavoriteState(name: String): Boolean = favoriteCheck(name) != null

    private suspend fun favoriteCheck(name: String): Favorite? =
        favoriteRepository.getFavoriteState(name)

}


class PeopleDetailsFactory(
    private val favoriteRepository: FavoriteRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeopleDetailsViewModel(favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}