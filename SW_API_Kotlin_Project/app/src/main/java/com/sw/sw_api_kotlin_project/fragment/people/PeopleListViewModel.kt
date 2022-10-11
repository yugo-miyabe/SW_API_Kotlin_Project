package com.sw.sw_api_kotlin_project.fragment.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.utils.PageType
import com.sw.sw_api_kotlin_project.utils.Resource
import kotlinx.coroutines.Dispatchers

class PeopleListViewModel(
    private val peopleRepository: PeopleRepository
) : BaseViewModel() {

    fun getPeople(pageType: PageType) = liveData(Dispatchers.IO) {
        pageParameterFormat(pageType)
        emit(Resource.loading(null))
        try {
            val response = peopleRepository.getPeople(page)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error"))
        }
    }

}

class PeopleListViewModelFactory(private val peopleRepository: PeopleRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeopleListViewModel(peopleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}