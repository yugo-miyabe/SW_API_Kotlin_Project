package com.sw.sw_api_kotlin_project.api

import com.sw.sw_api_kotlin_project.model.APIRoot
import com.sw.sw_api_kotlin_project.model.films.Films
import com.sw.sw_api_kotlin_project.model.peple.People
import com.sw.sw_api_kotlin_project.model.planet.Planet
import com.sw.sw_api_kotlin_project.model.species.Species
import com.sw.sw_api_kotlin_project.model.starships.Starships
import com.sw.sw_api_kotlin_project.model.vehicles.Vehicles
import retrofit2.Response
import retrofit2.http.GET


interface SWService {

    /**
     * root url 取得
     */
    @GET(".")
    suspend fun apiRoot(): Response<APIRoot>

    /**
     * 登場人物取得
     */
    @GET("people/")
    suspend fun people(): Response<People>

    /**
     * 映画情報取得
     */
    @GET("films/")
    suspend fun films(): Response<Films>

    /**
     * 惑星取得
     */
    @GET("planets/")
    suspend fun planets(): Response<Planet>

    /**
     * 種族取得
     */
    @GET("species/")
    suspend fun species(): Response<Species>

    /**
     * 宇宙船取得
     */
    @GET("starships/")
    suspend fun starships(): Response<Starships>

    /**
     * 車両取得
     */
    @GET("vehicles/")
    suspend fun vehicles(): Response<Vehicles>

}