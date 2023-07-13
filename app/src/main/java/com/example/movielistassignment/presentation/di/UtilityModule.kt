package com.example.movielistassignment.presentation.di

import android.app.Application
import android.content.res.Resources
import com.example.movielistassignment.R
import com.example.movielistassignment.presentation.utility.GridSpacingItemDecoration
import dagger.Module
import dagger.Provides

@Module
class UtilityModule {

    @Provides
    fun resources(application: Application): Resources = application.resources

    @Provides
    fun itemDecoration(resources: Resources): GridSpacingItemDecoration =
        GridSpacingItemDecoration(2, resources.getDimensionPixelOffset(R.dimen._10dp), true)
}