package com.example.exoplayer_mvvm_app.domain.repoository

import com.example.exoplayer_mvvm_app.data.local.MoviesDao
import com.example.exoplayer_mvvm_app.data.model.Movies
import com.example.exoplayer_mvvm_app.data.model.MoviesResponse
import com.example.exoplayer_mvvm_app.data.remote.MoviesApiService
import retrofit2.Response

class MoviesRepository(private val moviesApiService: MoviesApiService,private val moviesDao: MoviesDao) {

    suspend fun getMovies(): Response<MoviesResponse> = moviesApiService.getApiMovies()

    suspend fun saveBookMark(movies: Movies)=moviesDao.insert(movies)

    fun getBookMark()=moviesDao.getMoviesList()

}