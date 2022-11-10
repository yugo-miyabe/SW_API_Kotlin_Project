package com.sw.sw_api_kotlin_project.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {
    private val _favoriteList = MutableLiveData<List<Favorite>>()
    val favoriteList: LiveData<List<Favorite>> get() = _favoriteList
    private val _favoriteMessage = MutableLiveData<String>()
    val favoriteMessage get() = _favoriteMessage

    init {
        viewModelScope.launch {
            try {
                favoriteRepository.favoriteList.collect { favoriteList ->
                    _favoriteList.value = favoriteList
                }
            } catch (exception: Exception) {
                _favoriteMessage.value = exception.message
            }
        }
    }
}
