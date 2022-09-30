package com.sw.sw_api_kotlin_project.ui.others

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sw.sw_api_kotlin_project.base.BaseViewModel

class OthersViewModel : BaseViewModel() {
}

class OthersViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OthersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OthersViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}