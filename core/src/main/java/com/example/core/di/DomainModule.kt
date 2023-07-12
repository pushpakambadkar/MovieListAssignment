package com.example.core.di

import com.example.core.data.MovieRepositoryImpl
import com.example.core.data.datasource.MovieDataSourceImpl
import com.example.core.data.datasource.MovieRemoteDataSource
import com.example.core.data.service.MovieApi
import com.example.core.domain.MovieRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DomainModule {

    @Provides
    fun repository(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository = MovieRepositoryImpl(movieRemoteDataSource)

    @Provides
    fun dataSource(movieApi: MovieApi): MovieRemoteDataSource = MovieDataSourceImpl(movieApi)

}