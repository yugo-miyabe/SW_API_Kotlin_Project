package com.sw.sw_api_kotlin_project.model.films

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    @SerialName("episode_id")
    val episodeId: Int,
    @SerialName("opening_crawl")
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    @SerialName("release_date")
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)