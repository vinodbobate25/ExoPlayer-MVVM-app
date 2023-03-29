package com.example.newsapp.di


import com.example.exoplayer_mvvm_app.domain.repoository.MoviesRepository
import com.example.exoplayer_mvvm_app.ui.dashboard.DashboardViewModel
import com.example.exoplayer_mvvm_app.ui.home.BookmarkViewModel
import com.example.exoplayer_mvvm_app.ui.notifications.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MoviesRepository(get(), get())
    }
}

val viewModelModule = module {

    viewModel {
        DashboardViewModel(get(),get())
    }
    viewModel {
        BookmarkViewModel(get())
    }

    viewModel {
        DetailViewModel()
    }

}