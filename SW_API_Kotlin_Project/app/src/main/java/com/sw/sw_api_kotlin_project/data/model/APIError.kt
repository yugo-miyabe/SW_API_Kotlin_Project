package com.sw.sw_api_kotlin_project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class APIError(
    val detail: String
)
