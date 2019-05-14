package com.example.moviedb.data

import com.example.moviedb.data.model.MovieResponse
import io.reactivex.Single

interface MovieDataSource {
    interface Remote {
        fun getMovies(page: Int): Single<MovieResponse>
    }
}
