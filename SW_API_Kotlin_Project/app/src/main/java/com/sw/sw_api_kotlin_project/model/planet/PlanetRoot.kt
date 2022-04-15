package com.sw.sw_api_kotlin_project.model.planet

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetRoot(
    val count: Int,
    val next: String,
    val previous: String?,
    @SerialName("results")
    val planets: List<Planet>
)