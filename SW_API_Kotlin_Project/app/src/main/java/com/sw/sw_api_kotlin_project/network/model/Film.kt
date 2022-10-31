package com.sw.sw_api_kotlin_project.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
) : Parcelable