package com.sw.sw_api_kotlin_project.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
data class Species(
    @SerialName("average_height")
    val averageHeight: String,
    @SerialName("average_lifespan")
    val averageLifespan: String,
    val classification: String,
    val created: String,
    val designation: String,
    val edited: String,
    @SerialName("eye_colors")
    val eyeColors: String,
    val films: List<String>,
    @SerialName("hair_colors")
    val hairColors: String,
    @SerialName("homeworld")
    val homeWorld: String?,
    val language: String,
    val name: String,
    val people: List<String>,
    val skin_colors: String,
    val url: String
) : Parcelable