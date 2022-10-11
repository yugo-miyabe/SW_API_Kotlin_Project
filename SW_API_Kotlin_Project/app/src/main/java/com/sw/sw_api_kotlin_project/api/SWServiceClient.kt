package com.sw.sw_api_kotlin_project.api

import com.sw.sw_api_kotlin_project.api.network.SWService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SWServiceClient {
    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"

        fun getService(): SWService {
            val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return builder.create(SWService::class.java)
        }
    }
}

