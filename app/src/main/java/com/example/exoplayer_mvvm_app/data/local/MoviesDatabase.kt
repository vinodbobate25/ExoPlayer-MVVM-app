package com.example.exoplayer_mvvm_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.exoplayer_mvvm_app.data.model.Movies

@Database(entities = [Movies::class], version = 1, exportSchema = false)
@TypeConverters(GenreIdConvertor::class)
abstract class MoviesDatabase:RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}