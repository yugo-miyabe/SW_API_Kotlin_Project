package com.sw.sw_api_kotlin_project.screen.people.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.model.entity.RequestStatus
import com.sw.sw_api_kotlin_project.model.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.network.model.Results
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository
) : BaseViewModel() {
    private val _peopleList = MutableLiveData<Results<People>>()
    val peopleList: LiveData<Results<People>> get() = _peopleList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _failureMessage = MutableLiveData<String>()
    val failureMessage: LiveData<String> get() = _failureMessage

    fun getPeople(pageType: PageType) {
        pageParameterFormat(pageType)
        viewModelScope.launch {
            peopleRepository.getPeople(page).collect { response ->
                when (response.status) {
                    RequestStatus.SUCCESS -> {
                        _isLoading.value = false
                        _peopleList.value = response.data!!
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