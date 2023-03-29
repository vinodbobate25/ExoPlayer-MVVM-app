package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.exoplayer_mvvm_app.data.local.MoviesDao
import com.example.exoplayer_mvvm_app.data.local.MoviesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single {
        provideNewsDao(db = get())
    }
}

fun provideDatabase(application: Application): MoviesDatabase {
    return Room.databaseBuilder(application, MoviesDatabase::class.java, "Movies")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideNewsDao(db: MoviesDatabase): MoviesDao {
    return db.getMoviesDao()
}
