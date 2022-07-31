package com.sw.sw_api_kotlin_project.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.model.peple.PeopleRoot
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.utils.Result
import kotlinx.coroutines.launch

class PeopleListViewModel(private val apiRepository: APIRepository) : BaseViewModel() {
    private val _people = MutableLiveData<PeopleRoot?>()
    val people = _people

    fun fetchPeople() {
        startLoading()
        viewModelScope.launch {
            val api = SWServiceClient.getService()
            val response = api.people()
            when (val result = apiRepository.getResponse(response)) {
                is Result.Success -> {
                    _people.value = result.data
                    stopLoading()
                }
                is Result.Error -> {
                    // TODO エラーハンドリング
                    result.type
                    stopLoading()
                }
                else -> {
                    // ここには来ない
                }
            }
        }
    }
}

class PeopleListViewModelFactory(private val apiRepository: APIRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeopleListViewModel(apiRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}