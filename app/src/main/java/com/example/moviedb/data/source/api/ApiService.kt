package com.example.moviedb.data.source.api

import com.example.moviedb.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    fun getMovies(@Query("page") page: Int): Single<MovieResponse>
}
