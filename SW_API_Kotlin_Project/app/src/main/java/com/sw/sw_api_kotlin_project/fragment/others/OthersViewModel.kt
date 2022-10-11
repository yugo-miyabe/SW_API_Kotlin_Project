package com.sw.sw_api_kotlin_project.fragment.others

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import kotlinx.coroutines.launch

class OthersViewModel(private val favoriteRepository: FavoriteRepository) : BaseViewModel() {

    fun delete() {
        viewModelScope.launch {
            favoriteRepository.deleteAll()
        }
    }
}

class OthersViewModelFactory(
    private val favoriteRepository: FavoriteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OthersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OthersViewModel(favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}