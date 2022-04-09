package com.sw.sw_api_kotlin_project.model.planet

import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)