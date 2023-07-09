package com.sw.sw_api_kotlin_project.screen.home

import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import com.sw.sw_api_kotlin_project.screen.home.HomeFragmentDirections.Companion.actionNavHomeListToNavFilmsList
import com.sw.sw_api_kotlin_project.screen.home.HomeFragmentDirections.Companion.actionNavHomeListToNavPeopleList
import com.sw.sw_api_kotlin_project.screen.home.HomeFragmentDirections.Companion.actionNavHomeListToNavPlanetList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    fun onTapPeopleList() = addNavigationEvent(actionNavHomeListToNavPeopleList())

    fun onTapFilmList() = addNavigationEvent(actionNavHomeListToNavFilmsList())

    fun onTapPlanetList() = addNavigationEvent(actionNavHomeListToNavPlanetList())

}
