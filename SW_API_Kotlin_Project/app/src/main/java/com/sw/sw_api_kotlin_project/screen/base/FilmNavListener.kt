package com.sw.sw_api_kotlin_project.screen.base

import com.sw.sw_api_kotlin_project.data.network.model.Film

interface FilmNavListener {
    fun getFilmValue(): Film
}