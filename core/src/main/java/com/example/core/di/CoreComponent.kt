package com.example.core.di

import com.example.core.domain.usecase.GetMovieUseCase
import dagger.Component
import dagger.Subcomponent

@Component(modules = [UseCaseModule::class])
interface CoreComponent {

    fun getMovieUseCase(): GetMovieUseCase
}