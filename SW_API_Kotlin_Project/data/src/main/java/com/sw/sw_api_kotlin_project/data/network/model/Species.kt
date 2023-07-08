package com.sw.sw_api_kotlin_project.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Species(
    @SerializedName("average_height")
    val averageHeight: String,
    @SerializedName("average_lifespan")
    val averageLifespan: String,
    val classification: String,
    val created: String,
    val designation: String,
    val edited: String,
    @SerializedName("eye_colors")
    val eyeColors: String,
    val films: List<String>,
    @SerializedName("hair_colors")
    val hairColors: String,
    @SerializedName("homeworld")
    val homeWorld: String?,
    val language: String,
    val name: String,
    val people: List<String>,
    val skin_colors: String,
    val url: String
) : Parcelable