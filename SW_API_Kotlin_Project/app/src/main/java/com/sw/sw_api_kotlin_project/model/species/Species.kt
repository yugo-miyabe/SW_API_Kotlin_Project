package com.sw.sw_api_kotlin_project.model.species

import kotlinx.serialization.Serializable

@Serializable
data class Species(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)