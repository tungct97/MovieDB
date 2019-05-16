package com.example.moviedb.data

import androidx.lifecycle.LiveData
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import io.reactivex.Completable
import io.reactivex.Single

interface MovieDataSource {
    interface Remote {
        fun getMovies(page: Int): Single<MovieResponse>
    }

    interface Local {
        fun insertMovie(movie: Movie): Completable

        fun deleteMovie(movie: Movie): Completable

        fun getMovies(): LiveData<List<Movie>>

        fun getFavouriteById(id: Int): Single<Movie>
    }
}
