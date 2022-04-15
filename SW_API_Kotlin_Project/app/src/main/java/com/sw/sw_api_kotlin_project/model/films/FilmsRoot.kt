package com.sw.sw_api_kotlin_project.model.films

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsRoot(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerialName("results")
    val films: List<Films>
)