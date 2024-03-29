package com.sw.sw_api_kotlin_project.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Starships(
    /**
     * 近代銀河光時間
     */
    @SerializedName("MGLT")
    val mglt: String,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    val consumables: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    @SerializedName("hyperdrive_rating")
    val hyperDriveRating: String,
    val length: String,
    val manufacturer: String,
    @SerializedName("max_atmosphering_speed")
    val maxAtmosphereSpeed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starship_class: String,
    val url: String
) : Parcelable
