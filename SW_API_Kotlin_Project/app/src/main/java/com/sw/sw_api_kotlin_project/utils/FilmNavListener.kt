package com.sw.sw_api_kotlin_project.utils

import com.sw.sw_api_kotlin_project.data.model.Film

interface FilmNavListener {
    fun getFilmValue(): Film
}