package com.sw.sw_api_kotlin_project.ui.people

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class PeopleListViewModel() : BaseViewModel() {
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

    }
}

class PeopleListViewModelFactory() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeopleListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}