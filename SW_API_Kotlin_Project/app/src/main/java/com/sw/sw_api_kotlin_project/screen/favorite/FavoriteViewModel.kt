package com.sw.sw_api_kotlin_project.screen.favorite

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.model.entity.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.model.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {
    private val _favoriteList = MutableLiveData<List<Favorite>>()
    val favoriteList: LiveData<List<Favorite>> get() = _favoriteList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _failureMessage = MutableLiveData<String>()
    val failureMessage: LiveData<String> get() = _failureMessage

    fun getFavoriteList(viewLifecycleOwner: LifecycleOwner) {
        val favoriteListObserver = object : SWLiveDataObserver<List<Favorite>>() {
            override fun onSuccess(data: List<Favorite>?) {
                _favoriteList.value = data!!
                _isLoading.value = true
            }

            override fun onError(errorMessage: String) {
                _isLoading.value = true
                _failureMessage.value = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                _isLoading.value = false
                _failureMessage.value = ""
            }
        }
        favoriteRepository.getAll().observe(viewLifecycleOwner, favoriteListObserver)
    }

}
