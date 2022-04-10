package com.sw.sw_api_kotlin_project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIRoot(
    @SerialName("films")
    val filmsUrl: String,
    @SerialName("people")
    val peopleUrl: String,
    @SerialName("planets")
    val planetsUrl: String,
    @SerialName("species")
    val speciesUrl: String,
    @SerialName("starships")
    val starshipsUrl: String,
    @SerialName("vehicles")
    val vehiclesUrl: String
)