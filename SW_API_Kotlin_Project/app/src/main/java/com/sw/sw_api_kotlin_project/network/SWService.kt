package com.sw.sw_api_kotlin_project.network

import com.sw.sw_api_kotlin_project.data.model.APIRoot
import com.sw.sw_api_kotlin_project.data.model.Films
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.data.model.Species
import com.sw.sw_api_kotlin_project.data.model.Starships
import com.sw.sw_api_kotlin_project.data.model.Vehicles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SWService {

    /**
     * root url 取得
     */
    @GET(".")
    suspend fun apiRoot(): Response<APIRoot>

    /**
     * 登場人物取得
     */
    @GET("people")
    suspend fun getPeople(@Query("page") page: Int): Results<People>

    /**
     * 登場人物検索
     */
    @GET("people")
    suspend fun getPeopleSearch(@Query("search") search: String): Response<Results<People>>

    /**
     * 映画情報取得
     */
    @GET("films")
    suspend fun getFilms(@Query("page") page: Int): Results<Films>

    /**
     * 映画情報検索
     */
    @GET("films")
    suspend fun getFilmsSearch(@Query("search") search: String): Results<Films>

    /**
     * 惑星取得
     */
    @GET("planets")
    suspend fun getPlanets(@Query("page") page: Int): Results<Planet>

    /**
     * 種族取得
     */
    @GET("species")
    suspend fun species(): Response<Results<Planet>>

    /**
     * 宇宙船取得
     */
    @GET("starships")
    suspend fun getStarShips(@Query("page") page: Int): Results<Starships>

    @GET
    suspend fun getSpeciesByUrl(@Url speciesUrl: String): Species

    /**
     * 車両取得
     */
    @GET("vehicles")
    suspend fun vehicles(): Response<Results<Vehicles>>

}