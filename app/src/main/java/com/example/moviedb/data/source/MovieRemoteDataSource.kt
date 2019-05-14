package com.example.moviedb.data.source

import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.model.MovieResponse
import com.example.moviedb.data.source.api.ApiService
import io.reactivex.Single

class MovieRemoteDataSource(private val apiService: ApiService) : MovieDataSource.Remote {
    override fun getMovies(page: Int): Single<MovieResponse> {
        return apiService.getMovies(page)
    }
}
