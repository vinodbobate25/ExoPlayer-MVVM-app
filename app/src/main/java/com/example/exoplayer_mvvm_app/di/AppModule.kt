package com.example.newsapp.di

import com.example.exoplayer_mvvm_app.data.remote.MoviesApiService
import com.example.exoplayer_mvvm_app.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single {
        provideHttpClient()
    }
    single {
        provideRetrofit(get())
    }
    single {
        provideApiService(get())
    }

}

fun provideHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    return okHttpClientBuilder.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}

fun provideApiService(retrofit: Retrofit) = retrofit.create(MoviesApiService::class.java)
