package com.example.movielistassignment.presentation.di

import com.example.core.data.datasource.MovieRemoteDataSource
import com.example.core.di.CoreComponent
import com.example.core.di.DomainModule
import com.example.core.di.NetworkModule
import com.example.core.di.UseCaseModule
import com.example.core.domain.MovieRepository
import com.example.movielistassignment.presentation.movies.MovieListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class], dependencies = [CoreComponent::class])
interface AppComponent {

    fun inject(fragment: MovieListFragment)

    @Component.Factory
    interface Factory {
        // Takes an instance of AppComponent when creating
        // an instance of LoginComponent
        fun create(coreComponent: CoreComponent): AppComponent
    }

}