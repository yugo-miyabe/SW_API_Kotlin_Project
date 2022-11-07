package com.sw.sw_api_kotlin_project.screen.film.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.model.entity.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.model.repository.FilmRepository
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.Results
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val filmsRepository: FilmRepository
) : BaseViewModel() {
    private val _filmList = MutableLiveData<Results<Film>>()
    val filmList: LiveData<Results<Film>> get() = _filmList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _failureMessage = MutableLiveData<String>()
    val failureMessage: LiveData<String> get() = _failureMessage

    fun getFilm(viewLifecycleOwner: LifecycleOwner, pageType: PageType) {
        pageParameterFormat(pageType)
        val peopleObserver = object : SWLiveDataObserver<Results<Film>>() {
            override fun onSuccess(data: Results<Film>?) {
                _filmList.value = data!!
                _isLoading.value = false
            }

            override fun onError(errorMessage: String) {
                _isLoading.value = false
                _failureMessage.value = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                _isLoading.value = true
                _failureMessage.value = ""
            }
        }
        viewModelScope.launch {
            filmsRepository.getFilm(page).observe(viewLifecycleOwner, peopleObserver)
        }
    }
}
