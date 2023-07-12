package com.example.core.data.service

import com.example.core.domain.models.Configurations
import com.example.core.domain.models.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getMovies(@Query("page") page: Int, @Query("language") language: String = "en-US"): MovieEntity

    @GET("configuration")
    suspend fun getConfigurations(): Configurations
}