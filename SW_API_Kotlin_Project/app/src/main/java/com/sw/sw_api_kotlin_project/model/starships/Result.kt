package com.sw.sw_api_kotlin_project.model.starships

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    /**
     * 近代銀河光時間
     */
    @SerialName("MGLT")
    val mglt: String,
    @SerialName("cargo_capacity")
    val cargoCapacity: String,
    val consumables: String,
    @SerialName("cost_in_credits")
    val costInCredits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    @SerialName("hyperdrive_rating")
    val hyperDriveRating: String,
    val length: String,
    val manufacturer: String,
    @SerialName("max_atmosphering_speed")
    val maxAtmosphereSpeed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starship_class: String,
    val url: String
)