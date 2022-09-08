package com.sw.sw_api_kotlin_project.ui.films_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sw.sw_api_kotlin_project.base.BaseViewModel

class FilmsDetailsViewModel : BaseViewModel() {
}


class FilmsDetailsFactory() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmsDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilmsDetailsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}