package com.example.exoplayer_mvvm_app.domain.usecases

import com.example.exoplayer_mvvm_app.data.model.Movies
import com.example.exoplayer_mvvm_app.domain.repoository.MoviesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveBooKMarkUseCase(): KoinComponent {
    private  val moviesRepository: MoviesRepository by inject()
    suspend operator fun invoke(movies: Movies) =moviesRepository.saveBookMark(movies)
}