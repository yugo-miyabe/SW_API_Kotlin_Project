package com.sw.sw_api_kotlin_project.data.network.model

import com.sw.sw_api_kotlin_project.data.network.model.APIRoot
import com.sw.sw_api_kotlin_project.data.network.model.Film
import com.sw.sw_api_kotlin_project.data.network.model.People
import com.sw.sw_api_kotlin_project.data.network.model.Planet
import com.sw.sw_api_kotlin_project.data.network.model.Results
import com.sw.sw_api_kotlin_project.data.network.model.Starships
import com.sw.sw_api_kotlin_project.data.network.model.Vehicles
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
    @GET("people")
    suspend fun getPeople(@Query("page") page: Int): Results<People>

    /**
     * 登場人物検索
     */
    @GET("people")
    suspend fun getPeopleSearch(@Query("search") search: String): Results<People>

    /**
     * 映画情報取得
     */
    @GET("films")
    suspend fun getFilms(@Query("page") page: Int): Results<Film>

    /**
     * 映画情報検索
     */
    @GET("films")
    suspend fun getFilmsSearch(@Query("search") search: String): Results<Film>

    /**
     * 惑星情報取得
     */
    @GET("planets")
    suspend fun getPlanets(@Query("page") page: Int): Results<Planet>

    /**
     * 惑星情報検索
     */
    @GET("planets")
    suspend fun getPlanetsSearch(@Query("search") search: String): Results<Planet>

    /**
     * 種族情報取得
     */
    @GET("species")
    suspend fun getSpecies(@Query("page") page: Int): Results<Planet>

    /**
     * 種族情報検索
     */
    @GET("species")
    suspend fun getSpeciesSearch(@Query("search") search: String): Results<Planet>

    /**
     * 宇宙船情報取得
     */
    @GET("starships")
    suspend fun getStarShips(@Query("page") page: Int): Results<Starships>

    /**
     * 宇宙船情報検索
     */
    @GET("starships")
    suspend fun getStarShipsSearch(@Query("search") search: String): Results<Starships>

    /**
     * 車両情報取得
     */
    @GET("vehicles")
    suspend fun getVehicles(@Query("page") page: Int): Results<Vehicles>

    /**
     * 車両情報検索
     */
    @GET("vehicles")
    suspend fun getVehiclesSearch(@Query("search") search: String): Results<Vehicles>
}
