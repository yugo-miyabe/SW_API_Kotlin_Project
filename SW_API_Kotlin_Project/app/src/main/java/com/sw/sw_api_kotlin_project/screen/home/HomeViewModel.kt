package com.sw.sw_api_kotlin_project.screen.home

import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import com.sw.sw_api_kotlin_project.screen.home.HomeFragmentDirections.Companion.actionHomeListToFilmsList
import com.sw.sw_api_kotlin_project.screen.home.HomeFragmentDirections.Companion.actionHomeListToPeopleList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    fun onTapPeopleList() = addNavigationEvent(actionHomeListToPeopleList())

    fun onTapFilmList() = addNavigationEvent(actionHomeListToFilmsList())

    fun onTapPlanetList() = addNavigationEvent(actionHomeListToFilmsList())

}
