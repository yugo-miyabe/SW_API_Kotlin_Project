package com.sw.sw_api_kotlin_project.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class APIRoot(
    @SerializedName("people")
    val peopleUrl: String,
    @SerializedName("planets")
    val planetsUrl: String,
    @SerializedName("films")
    val filmsUrl: String,
    @SerializedName("species")
    val speciesUrl: String,
    @SerializedName("vehicles")
    val vehiclesUrl: String,
    @SerializedName("starships")
    val starshipsUrl: String,
) : Parcelable