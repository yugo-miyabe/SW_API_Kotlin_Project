package com.sw.sw_api_kotlin_project.model.films

import kotlinx.serialization.Serializable

@Serializable
data class Films(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)