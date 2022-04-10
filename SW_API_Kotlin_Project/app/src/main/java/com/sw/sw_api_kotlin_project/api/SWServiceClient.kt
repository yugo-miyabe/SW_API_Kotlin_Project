package com.sw.sw_api_kotlin_project.api

import retrofit2.Retrofit

class SWServiceClient {
    companion object {
        fun getService(): SWService {
            val builder = Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .build()
            return builder.create(SWService::class.java)
        }
    }
}

