package com.example.movielistassignment

import android.app.Application
import com.example.core.di.DaggerCoreComponent
import com.example.movielistassignment.presentation.di.AppComponent
import com.example.movielistassignment.presentation.di.AppModule
import com.example.movielistassignment.presentation.di.DaggerAppComponent

class MovieApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(DaggerCoreComponent.create(), AppModule(this))
    }

}