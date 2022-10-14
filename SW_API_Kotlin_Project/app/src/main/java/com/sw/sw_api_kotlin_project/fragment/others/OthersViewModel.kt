package com.sw.sw_api_kotlin_project.fragment.others

import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OthersViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    fun delete() {
        viewModelScope.launch {
            favoriteRepository.deleteAll()
        }
    }
}
