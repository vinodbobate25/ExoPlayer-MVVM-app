package com.example.exoplayer_mvvm_app.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.exoplayer_mvvm_app.data.model.Movies

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMoviesList(): LiveData<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: Movies): Long

    @Delete
    suspend fun delete(movies: Movies)

    @Query("DELETE FROM Movies")
    suspend fun deleteAllMoviess()
}