package com.sw.sw_api_kotlin_project.screen.people.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.data.model.entity.Favorite
import com.sw.sw_api_kotlin_project.data.model.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.data.network.model.People
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModelTest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailsViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModelTest() {
    private val _favoriteStatus = MutableLiveData<Boolean>()
    val favoriteStatus: LiveData<Boolean> = _favoriteStatus

    fun getFavoriteState(name: String) {
        viewModelScope.launch {
            favoriteRepository.get(name).collect { favorite ->
                _favoriteStatus.value = favorite != null
            }
        }
    }

    fun toggleFavorite(people: People) {
        viewModelScope.launch {
            val favorite: Favorite? = favoriteRepository.get(people.name).first()
            if (favorite == null) {
                favoriteRepository.add(people)
            } else {
                favoriteRepository.delete(favorite)
            }
            getFavoriteState(people.name)
        }
    }

}
