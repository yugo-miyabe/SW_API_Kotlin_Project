package com.sw.sw_api_kotlin_project.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sw.sw_api_kotlin_project.base.BaseViewModel

class FavoriteViewModel : BaseViewModel() {
}

class FavoriteFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}