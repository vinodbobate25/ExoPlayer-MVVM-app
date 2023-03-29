package com.example.exoplayer_mvvm_app.domain.usecases

import com.example.exoplayer_mvvm_app.data.model.MoviesResponse
import com.example.exoplayer_mvvm_app.domain.repoository.MoviesRepository
import com.example.exoplayer_mvvm_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import java.io.IOException

class GetMoviesUseCase : KoinComponent {
    private val moviesRepository: MoviesRepository by inject()

    suspend operator fun invoke(): Flow<Resource<MoviesResponse>> = flow {
        kotlin.runCatching {
            moviesRepository.getMovies()
        }.onSuccess {
            emit(getMoviesList(it))
        }.onFailure {
            if (it is IOException) {
                emit(Resource.Error("Network Failure"))
            } else {
                emit(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun getMoviesList(response: Response<MoviesResponse>): Resource<MoviesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}