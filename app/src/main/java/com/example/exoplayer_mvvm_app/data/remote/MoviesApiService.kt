package com.example.exoplayer_mvvm_app.data.remote

import com.example.exoplayer_mvvm_app.data.model.MoviesResponse
import com.example.exoplayer_mvvm_app.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("3/trending/all/day")
    suspend fun getApiMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MoviesResponse>
}