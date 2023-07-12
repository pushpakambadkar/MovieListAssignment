package com.example.core.domain

import com.example.core.domain.models.Configurations
import com.example.core.domain.models.MovieEntity

interface MovieRepository {

    suspend fun getMovies(page: Int = 1): com.example.core.Result<MovieEntity>

    suspend fun getConfigurations(): com.example.core.Result<Configurations>
}