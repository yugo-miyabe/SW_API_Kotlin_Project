package com.sw.sw_api_kotlin_project.model.entity

sealed class SWResult<out R> {
    data class Success<out T>(val data: T) : SWResult<T>()
    data class Error(val exception: Exception) : SWResult<Nothing>()
    object Loading : SWResult<Nothing>()
}