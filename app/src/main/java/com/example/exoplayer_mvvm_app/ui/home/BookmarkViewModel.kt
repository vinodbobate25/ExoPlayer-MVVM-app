package com.example.exoplayer_mvvm_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exoplayer_mvvm_app.domain.usecases.GetBookMarkUseCase

class BookmarkViewModel(private val getBookMarkUseCase: GetBookMarkUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchMovies() = getBookMarkUseCase.invoke()

}