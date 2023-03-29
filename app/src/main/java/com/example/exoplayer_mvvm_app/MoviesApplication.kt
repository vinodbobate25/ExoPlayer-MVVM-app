package com.example.exoplayer_mvvm_app

import android.app.Application
import com.example.newsapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(appModule, databaseModule, repositoryModule, viewModelModule, usecaseModule)
        }
    }
}