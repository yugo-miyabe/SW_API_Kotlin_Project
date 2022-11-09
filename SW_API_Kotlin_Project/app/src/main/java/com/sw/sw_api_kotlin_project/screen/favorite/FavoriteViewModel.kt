package com.sw.sw_api_kotlin_project.screen.favorite

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.SWLiveDataObserver
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

    fun getFavoriteList(viewLifecycleOwner: LifecycleOwner) {
        val favoriteListObserver = object : SWLiveDataObserver<List<Favorite>>() {
            override fun onSuccess(data: List<Favorite>?) {
                _favoriteList.value = data!!
            }

            override fun onError(errorMessage: String) {
                _favoriteMessage.value = errorMessage
            }
        }

        viewModelScope.launch {
            favoriteRepository.getAll().observe(viewLifecycleOwner, favoriteListObserver)
        }
    }

}
