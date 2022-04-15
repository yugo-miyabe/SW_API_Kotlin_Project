package com.sw.sw_api_kotlin_project.model.peple

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleRoot(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerialName("results")
    val people: List<People>
)