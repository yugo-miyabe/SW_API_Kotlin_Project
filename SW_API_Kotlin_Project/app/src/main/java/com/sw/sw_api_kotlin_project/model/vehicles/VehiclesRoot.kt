package com.sw.sw_api_kotlin_project.model.vehicles

import kotlinx.serialization.Serializable

@Serializable
data class VehiclesRoot(
    val count: Int,
    val next: String,
    val previous: String?,
    val vehicles: List<Vehicles>
)