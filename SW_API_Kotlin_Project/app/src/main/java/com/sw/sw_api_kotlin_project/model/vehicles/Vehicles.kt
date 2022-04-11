package com.sw.sw_api_kotlin_project.model.vehicles

import kotlinx.serialization.Serializable

@Serializable
data class Vehicles(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)