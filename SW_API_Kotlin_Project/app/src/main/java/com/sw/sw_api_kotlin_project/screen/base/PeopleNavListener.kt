package com.sw.sw_api_kotlin_project.screen.base

import com.sw.sw_api_kotlin_project.network.model.People

interface PeopleNavListener {
    fun getPeopleValue(): People
}