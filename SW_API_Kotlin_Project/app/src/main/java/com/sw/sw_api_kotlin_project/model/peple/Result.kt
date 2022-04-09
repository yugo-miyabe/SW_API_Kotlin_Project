package com.sw.sw_api_kotlin_project.model.peple

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("birth_year")
    val birthYear: String,
    val created: String,
    val edited: String,
    @SerialName("eye_color")
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    @SerialName("hairColor")
    val hairColor: String,
    val height: String,
    @SerialName("homeworld")
    val homeWorld: String,
    val mass: String,
    val name: String,
    @SerialName("skin_color")
    val skinColor: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)