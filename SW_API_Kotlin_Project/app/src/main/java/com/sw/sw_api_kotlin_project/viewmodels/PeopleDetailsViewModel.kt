package com.sw.sw_api_kotlin_project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PeopleDetailsViewModel : ViewModel() {


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