package com.sw.sw_api_kotlin_project.screen.film.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.model.entity.RequestStatus
import com.sw.sw_api_kotlin_project.model.repository.FilmRepository
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.Results
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val filmRepository: FilmRepository
) : BaseViewModel() {
    private val _filmList = MutableLiveData<Results<Film>>()
    val filmList: LiveData<Results<Film>> get() = _filmList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _failureMessage = MutableLiveData<String>()
    val failureMessage: LiveData<String> get() = _failureMessage

    fun getFilm(pageType: PageType) {
        pageParameterFormat(pageType)
        viewModelScope.launch {
            filmRepository.getFilm(page).collect { response ->
                when (response.status) {
                    RequestStatus.SUCCESS -> {
                        _isLoading.value = false
                        _filmList.value = response.data!!
                    }
                    RequestStatus.ERROR -> {
                        _isLoading.value = false
                        _failureMessage.value = response.message!!
                    }
                    RequestStatus.LOADING -> {
                        _isLoading.value = true
                        _failureMessage.value = ""
                    }
                }
            }
        }
    }
}
