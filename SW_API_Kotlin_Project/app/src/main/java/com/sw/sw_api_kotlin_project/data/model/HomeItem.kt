package com.sw.sw_api_kotlin_project.data.model

import android.graphics.drawable.Drawable
import com.sw.sw_api_kotlin_project.utils.ListType

data class HomeItem(
    val image: Drawable?,
    val title: String,
    val listType: ListType
)
