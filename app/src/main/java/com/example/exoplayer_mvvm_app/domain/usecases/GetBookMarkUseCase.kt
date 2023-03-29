package com.example.exoplayer_mvvm_app.domain.usecases

import com.example.exoplayer_mvvm_app.domain.repoository.MoviesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetBookMarkUseCase:KoinComponent {
    private  val moviesRepository:MoviesRepository by inject()
    operator fun invoke()=moviesRepository.getBookMark()
}