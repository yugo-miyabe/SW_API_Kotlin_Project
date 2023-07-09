package com.sw.sw_api_kotlin_project.screen.others

import android.provider.Settings.Global.getString
import androidx.lifecycle.viewModelScope
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.entity.WebViewInfo
import com.sw.sw_api_kotlin_project.data.model.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import com.sw.sw_api_kotlin_project.screen.others.OthersFragmentDirections.Companion.actionNavOtherToNavWebView
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


    fun onTapDirection(title:String) {
        val swApiDocumentation = "https://swapi.dev/documentation"

        addNavigationEvent(actionNavOtherToNavWebView(
            WebViewInfo(
                title = title,
                url = swApiDocumentation,
            )
        ))
    }
}
