package com.sw.sw_api_kotlin_project.screen.film.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sw.sw_api_kotlin_project.data.model.entity.PageType
import com.sw.sw_api_kotlin_project.data.model.entity.RequestStatus
import com.sw.sw_api_kotlin_project.data.model.repository.FilmRepository
import com.sw.sw_api_kotlin_project.data.network.model.Film
import com.sw.sw_api_kotlin_project.data.network.model.Results
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    val filmItems: Flow<PagingData<Film>> = Pager(config = PagingConfig(
        pageSize = 1,
        enablePlaceholders = false,
    ), pagingSourceFactory = {
        filmRepository.filmListPagingSource()
    }).flow.cachedIn(viewModelScope)

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
