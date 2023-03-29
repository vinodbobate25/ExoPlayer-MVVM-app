package com.example.newsapp.di


import com.example.exoplayer_mvvm_app.domain.usecases.GetBookMarkUseCase
import com.example.exoplayer_mvvm_app.domain.usecases.GetMoviesUseCase
import com.example.exoplayer_mvvm_app.domain.usecases.SaveBooKMarkUseCase
import org.koin.dsl.module

val usecaseModule = module {
    single { GetMoviesUseCase() }
    single { GetBookMarkUseCase() }
    single { SaveBooKMarkUseCase() }
}