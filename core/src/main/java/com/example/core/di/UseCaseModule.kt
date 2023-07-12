package com.example.core.di

import com.example.core.domain.MovieRepository
import com.example.core.domain.usecase.GetMovieUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [DomainModule::class])
class UseCaseModule {
    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMovieUseCase = GetMovieUseCase(movieRepository)
}