package com.sw.sw_api_kotlin_project.model.starships

import kotlinx.serialization.Serializable

@Serializable
data class StarshipsRoot(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Starships>
)