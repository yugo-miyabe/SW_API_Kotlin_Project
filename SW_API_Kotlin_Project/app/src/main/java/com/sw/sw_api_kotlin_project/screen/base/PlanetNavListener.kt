package com.sw.sw_api_kotlin_project.screen.base

import com.sw.sw_api_kotlin_project.network.model.Planet

interface PlanetNavListener {
    fun getPlanetValue(): Planet
}