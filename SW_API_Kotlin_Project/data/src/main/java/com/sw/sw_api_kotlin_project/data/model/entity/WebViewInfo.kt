package com.sw.sw_api_kotlin_project.data.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WebViewInfo(
    val title: String,
    val url: String
) : Parcelable
