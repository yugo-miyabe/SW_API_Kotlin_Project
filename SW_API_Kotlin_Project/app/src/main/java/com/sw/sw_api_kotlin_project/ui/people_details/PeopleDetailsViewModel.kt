package com.sw.sw_api_kotlin_project.ui.people_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.StarShipsRepository
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class PeopleDetailsViewModel(private val starShipsRepository: StarShipsRepository) :
    BaseViewModel() {

    fun getStarShips() {

    }

    fun getStarshipsByUrl(starshipUrl: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
        } catch (e: Exception) {
        }
    }

}


class PeopleDetailsFactory(private val starShipsRepository: StarShipsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeopleDetailsViewModel(starShipsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}