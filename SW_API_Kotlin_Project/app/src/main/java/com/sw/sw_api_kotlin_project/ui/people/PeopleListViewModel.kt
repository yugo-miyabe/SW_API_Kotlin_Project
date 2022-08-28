package com.sw.sw_api_kotlin_project.ui.people

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class PeopleListViewModel(private val apiRepository: APIRepository) : BaseViewModel() {
    private val _people = MutableLiveData<Results<People>?>()
    val people = _people

    fun getPeople(page: Int = 1) = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            val client = SWServiceClient.getService()
            val response = client.getPeople(page)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }

        /*
        startLoading()
        viewModelScope.launch {
            val api = SWServiceClient.getService()
            val response = api.getPeople(page)
            when (val result = apiRepository.fetchResponse(response)) {
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

         */


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