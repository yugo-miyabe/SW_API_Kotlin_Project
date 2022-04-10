package com.sw.sw_api_kotlin_project.model.error

import kotlinx.serialization.Serializable

@Serializable
data class APIError(
    val detail: String
)
