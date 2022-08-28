package com.sw.sw_api_kotlin_project.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sw.sw_api_kotlin_project.network.SWService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class SWServiceClient {
    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"

        fun getService(): SWService {
            val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()
            return builder.create(SWService::class.java)
        }
    }
}

