package com.sw.sw_api_kotlin_project.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.utils.PageType
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class PeopleListViewModel(
    private val peopleRepository: PeopleRepository,
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    fun getPeople(pageType: PageType) = liveData(Dispatchers.IO) {
        pageParameterFormat(pageType)
        emit(Resource.loading(data = null))
        try {
            val response = peopleRepository.getPeople(page)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

    suspend fun checkFavoriteState(name: String): Boolean = favoriteCheck(name) != null

    suspend fun addOrDeleteFavorite(name: String) {
        val favorite: Favorite? = favoriteCheck(name)
        if (favorite == null) {
            insert(Favorite(0, name))
        } else {
            delete(favorite)
        }
    }

    private suspend fun insert(favorite: Favorite) = favoriteRepository.insert(favorite)

    private suspend fun delete(favorite: Favorite) = favoriteRepository.delete(favorite)

    private suspend fun favoriteCheck(name: String): Favorite? =
        favoriteRepository.getPeopleFavoriteState(name = name)

}

class PeopleListViewModelFactory(
    private val peopleRepository: PeopleRepository,
    private val favoriteRepository: FavoriteRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeopleListViewModel(peopleRepository, favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}