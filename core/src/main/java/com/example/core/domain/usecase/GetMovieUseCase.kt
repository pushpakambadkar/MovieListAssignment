package com.example.core.domain.usecase

import com.example.core.domain.MovieRepository
import com.example.core.domain.models.Configurations
import com.example.core.domain.models.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieUseCase(private val movieRepo: MovieRepository) {

    suspend operator fun invoke(page: Int = 1): com.example.core.Result<MovieEntity> = withContext(Dispatchers.IO) {
        movieRepo.getMovies(page)
    }

    suspend fun getConfigurations(): com.example.core.Result<Configurations> = withContext(Dispatchers.IO) {
        movieRepo.getConfigurations()
    }
}