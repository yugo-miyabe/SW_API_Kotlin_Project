package com.sw.sw_api_kotlin_project.ui.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.FilmsRepository
import com.sw.sw_api_kotlin_project.utils.PageType
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class FilmsListViewModel(private val filmsRepository: FilmsRepository) : BaseViewModel() {

    fun getFilms(pageType: PageType) = liveData(Dispatchers.IO) {
        pageParameterFormat(pageType)
        emit(Resource.loading(null))
        try {
            val response = filmsRepository.getFilms(page)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }
}

class FilmsListViewModelFactory(private val filmsRepository: FilmsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilmsListViewModel(filmsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}