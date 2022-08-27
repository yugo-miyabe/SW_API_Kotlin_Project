package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.data.model.error.APIError
import com.sw.sw_api_kotlin_project.utils.Result
import kotlinx.serialization.json.Json
import retrofit2.Response

class APIRepository {
    fun <T> fetchResponse(response: Response<T>): Result<T> {
        return if (response.isSuccessful) {
            val body = response.body()!!
            Result.Success(body)
        } else {
            val errorBody = response.errorBody()!!.string()
            val errorResponse = Json.decodeFromString(APIError.serializer(), errorBody)
            Result.Error(errorResponse)
        }
    }
}