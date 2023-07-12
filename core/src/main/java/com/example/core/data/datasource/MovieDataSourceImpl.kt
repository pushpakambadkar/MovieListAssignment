package com.example.core.data.datasource

import com.example.core.Result
import com.example.core.data.service.MovieApi
import com.example.core.domain.models.Configurations
import com.example.core.domain.models.MovieEntity

class MovieDataSourceImpl(private val movieApi: MovieApi) : MovieRemoteDataSource {

    override suspend fun getMovies(page: Int): Result<MovieEntity> =
        try {
            val result = movieApi.getMovies(page)
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getConfigurations(): Result<Configurations> {
        return try {
            val result = movieApi.getConfigurations()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}