package com.sw.sw_api_kotlin_project.model.species

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesRoot(
    val count: Int,
    val next: String,
    val previous: String?,
    @SerialName("results")
    val species: List<Species>
)