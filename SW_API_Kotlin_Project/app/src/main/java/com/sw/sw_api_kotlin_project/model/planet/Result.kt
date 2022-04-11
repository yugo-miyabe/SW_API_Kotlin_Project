package com.sw.sw_api_kotlin_project.model.planet

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    @SerialName("orbital_period")
    val orbitalPeriod: String,
    val population: String,
    val residents: List<String>,
    @SerialName("rotation_period")
    val rotationPeriod: String,
    @SerialName("surface_water")
    val surfaceWater: String,
    val terrain: String,
    val url: String
)