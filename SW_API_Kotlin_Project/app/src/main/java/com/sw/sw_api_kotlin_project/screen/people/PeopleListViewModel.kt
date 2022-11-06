package com.sw.sw_api_kotlin_project.screen.people

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.model.entity.SWLiveDataObserver
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

    fun getPeople(viewLifecycleOwner: LifecycleOwner, pageType: PageType) {
        pageParameterFormat(pageType)

        val peopleObserver = object : SWLiveDataObserver<Results<People>>() {
            override fun onSuccess(data: Results<People>?) {
                _peopleList.value = data!!
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
            peopleRepository.getPeople(page).observe(viewLifecycleOwner, peopleObserver)
        }
    }

}