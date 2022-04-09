package com.sw.sw_api_kotlin_project.api

import com.sw.sw_api_kotlin_project.model.films.Films
import com.sw.sw_api_kotlin_project.model.peple.People
import retrofit2.Response
import retrofit2.http.GET


interface SWService {

    /**
     * 登場人物取得
     */
    @GET("people")
    suspend fun people(): Response<People>


    /**
     * 映画情報取得
     */
    @GET("films")
    suspend fun films(): Response<Films>

}