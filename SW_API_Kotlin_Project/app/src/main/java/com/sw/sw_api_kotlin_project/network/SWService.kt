package com.sw.sw_api_kotlin_project.network

import com.sw.sw_api_kotlin_project.model.APIRoot
import com.sw.sw_api_kotlin_project.model.films.FilmsRoot
import com.sw.sw_api_kotlin_project.model.peple.PeopleRoot
import com.sw.sw_api_kotlin_project.model.planet.PlanetRoot
import com.sw.sw_api_kotlin_project.model.species.SpeciesRoot
import com.sw.sw_api_kotlin_project.model.starships.StarshipsRoot
import com.sw.sw_api_kotlin_project.model.vehicles.VehiclesRoot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

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
    suspend fun fetchPeople(@Query("page") page: Int): Response<PeopleRoot>

    /**
     * 映画情報取得
     */
    @GET("films/")
    suspend fun fetchFilms(@Query("page") page: Int): Response<FilmsRoot>

    /**
     * 惑星取得
     */
    @GET("planets/")
    suspend fun fetchPlanets(@Query("page") page: Int): Response<PlanetRoot>

    /**
     * 種族取得
     */
    @GET("species/")
    suspend fun species(): Response<SpeciesRoot>

    /**
     * 宇宙船取得
     */
    @GET("starships/")
    suspend fun starships(): Response<StarshipsRoot>

    /**
     * 車両取得
     */
    @GET("vehicles/")
    suspend fun vehicles(): Response<VehiclesRoot>

}