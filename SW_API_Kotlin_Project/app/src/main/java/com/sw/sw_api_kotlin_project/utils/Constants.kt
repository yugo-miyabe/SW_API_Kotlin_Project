package com.sw.sw_api_kotlin_project.utils

import androidx.fragment.app.Fragment
import com.sw.sw_api_kotlin_project.fragments.FilmsFragment
import com.sw.sw_api_kotlin_project.fragments.PeopleListFragment
import com.sw.sw_api_kotlin_project.fragments.PlanetsFragment
import com.sw.sw_api_kotlin_project.fragments.SpeciesFragment
import com.sw.sw_api_kotlin_project.fragments.StarShipsFragment
import com.sw.sw_api_kotlin_project.fragments.VehiclesFragment

object Constants {
    val API_ROOT_LIST: List<String> = listOf(
        "people",
        "planets",
        "films",
        "species",
        "vehicles",
        "starships"
    )

    val FRAGMENT_LIST: Map<String, Fragment> = mapOf(
        API_ROOT_LIST[0] to PeopleListFragment(),
        API_ROOT_LIST[1] to PlanetsFragment(),
        API_ROOT_LIST[2] to FilmsFragment(),
        API_ROOT_LIST[3] to SpeciesFragment(),
        API_ROOT_LIST[4] to VehiclesFragment(),
        API_ROOT_LIST[5] to StarShipsFragment()
    )

}