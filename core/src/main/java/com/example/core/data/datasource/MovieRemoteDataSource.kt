package com.example.core.data.datasource

import com.example.core.domain.models.Configurations
import com.example.core.domain.models.MovieEntity

interface MovieRemoteDataSource  {

    suspend fun getMovies(page: Int): com.example.core.Result<MovieEntity>
    suspend fun getConfigurations(): com.example.core.Result<Configurations>
}