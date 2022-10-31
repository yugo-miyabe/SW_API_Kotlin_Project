package com.sw.sw_api_kotlin_project.model.entity

import android.graphics.drawable.Drawable

data class HomeItem(
    val image: Drawable?,
    val title: String,
    val listType: ListType
)
