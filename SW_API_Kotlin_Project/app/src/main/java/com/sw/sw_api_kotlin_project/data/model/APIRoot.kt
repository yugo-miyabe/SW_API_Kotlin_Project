package com.sw.sw_api_kotlin_project.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIRoot(
    @SerialName("people")
    val peopleUrl: String,
    @SerialName("planets")
    val planetsUrl: String,
    @SerialName("films")
    val filmsUrl: String,
    @SerialName("species")
    val speciesUrl: String,
    @SerialName("vehicles")
    val vehiclesUrl: String,
    @SerialName("starships")
    val starshipsUrl: String,
)