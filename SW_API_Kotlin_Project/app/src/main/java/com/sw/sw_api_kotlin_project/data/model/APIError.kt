package com.sw.sw_api_kotlin_project.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class APIError(
    val detail: String
) : Parcelable
