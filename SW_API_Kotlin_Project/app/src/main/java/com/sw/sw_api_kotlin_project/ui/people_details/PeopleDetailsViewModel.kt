package com.sw.sw_api_kotlin_project.ui.people_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sw.sw_api_kotlin_project.base.BaseViewModel

class PeopleDetailsViewModel : BaseViewModel() {


}


class PeopleDetailsFactory() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeopleDetailsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}