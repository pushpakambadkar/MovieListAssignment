package com.example.core.data

import com.example.core.Result
import com.example.core.data.datasource.MovieRemoteDataSource
import com.example.core.domain.MovieRepository
import com.example.core.domain.models.Configurations
import com.example.core.domain.models.MovieEntity

class MovieRepositoryImpl(private val movieDataSource: MovieRemoteDataSource): MovieRepository {

    override suspend fun getMovies(page: Int): Result<MovieEntity> {
        return movieDataSource.getMovies(page)
    }

    override suspend fun getConfigurations(): Result<Configurations> {
        return movieDataSource.getConfigurations()
    }
}