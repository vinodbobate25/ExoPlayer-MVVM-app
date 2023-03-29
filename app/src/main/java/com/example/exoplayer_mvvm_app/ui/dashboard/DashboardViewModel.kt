package com.example.exoplayer_mvvm_app.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exoplayer_mvvm_app.data.model.Movies
import com.example.exoplayer_mvvm_app.data.model.MoviesResponse
import com.example.exoplayer_mvvm_app.domain.usecases.GetMoviesUseCase
import com.example.exoplayer_mvvm_app.domain.usecases.SaveBooKMarkUseCase
import com.example.exoplayer_mvvm_app.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val saveBooKMarkUseCase: SaveBooKMarkUseCase
) : ViewModel() {

    private val _moviesListSate = MutableStateFlow<Resource<MoviesResponse>>(Resource.Loading())
    val moviesListSate: Flow<Resource<MoviesResponse>> = _moviesListSate

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    init {
        fetchMoviesList()
    }

    fun fetchMoviesList() = viewModelScope.launch {
        getMoviesUseCase.invoke().collect {
            _moviesListSate.value = it
        }
    }

    fun saveBookMark(movies: Movies) =
        viewModelScope.launch(Dispatchers.IO) { saveBooKMarkUseCase.invoke(movies) }
}