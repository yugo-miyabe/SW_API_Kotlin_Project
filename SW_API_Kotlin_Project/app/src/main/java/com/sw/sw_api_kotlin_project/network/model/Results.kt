package com.sw.sw_api_kotlin_project.network.model

data class Results<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>,
)