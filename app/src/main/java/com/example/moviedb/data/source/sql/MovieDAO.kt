package com.example.moviedb.data.source.sql

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedb.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getFavouriteById(id: Int): Single<Movie>
}
