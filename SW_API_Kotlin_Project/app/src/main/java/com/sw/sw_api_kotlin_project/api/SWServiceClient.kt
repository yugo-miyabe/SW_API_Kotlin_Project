package com.sw.sw_api_kotlin_project.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class SWServiceClient {
    companion object {
        fun getService(): SWService {
            val builder = Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()
            return builder.create(SWService::class.java)
        }
    }
}

