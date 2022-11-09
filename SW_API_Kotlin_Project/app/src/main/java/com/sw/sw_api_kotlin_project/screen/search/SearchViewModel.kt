package com.sw.sw_api_kotlin_project.screen.search

import android.os.Parcelable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.model.repository.SearchRepository
import com.sw.sw_api_kotlin_project.network.model.Results
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseViewModel() {
    private val _searchResultList = MutableLiveData<List<Results<out Parcelable>>>()
    val searchResultList: LiveData<List<Results<out Parcelable>>> get() = _searchResultList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage get() = _errorMessage

    fun getSearchResult(viewLifecycleOwner: LifecycleOwner, searchString: String) {
        val searchObserver = object : SWLiveDataObserver<List<Results<out Parcelable>>>() {
            override fun onSuccess(data: List<Results<out Parcelable>>?) {
                _isLoading.value = false
                _searchResultList.value = data!!
            }

            override fun onError(errorMessage: String) {
                _isLoading.value = false
                _errorMessage.value = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                _isLoading.value = true
            }
        }

        viewModelScope.launch {
            searchRepository.getSearchResult(searchString)
                .observe(viewLifecycleOwner, searchObserver)
        }
    }

}
