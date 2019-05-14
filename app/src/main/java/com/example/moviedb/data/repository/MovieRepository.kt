package com.example.moviedb.data.repository

import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.model.MovieResponse
import io.reactivex.Single

class MovieRepository(private val remote: MovieDataSource.Remote) : MovieDataSource.Remote {
    override fun getMovies(page: Int): Single<MovieResponse> {
        return remote.getMovies(page)
    }
}
