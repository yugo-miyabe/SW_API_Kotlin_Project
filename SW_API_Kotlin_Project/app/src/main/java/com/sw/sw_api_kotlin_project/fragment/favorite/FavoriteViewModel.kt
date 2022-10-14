package com.sw.sw_api_kotlin_project.fragment.favorite

import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    fun getFavoriteList() = liveData(Dispatchers.IO) {
        try {
            val response = favoriteRepository.getAll()
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

}
