package com.sw.sw_api_kotlin_project.model.starships

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StarshipsRoot(
    val count: Int,
    val next: String,
    val previous: String?,
    @SerialName("results")
    val starships: List<Starships>
)