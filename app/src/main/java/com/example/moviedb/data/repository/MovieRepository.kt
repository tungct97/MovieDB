package com.example.moviedb.data.repository

import androidx.lifecycle.LiveData
import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import io.reactivex.Single

class MovieRepository(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local
) :
    MovieDataSource.Remote, MovieDataSource.Local {
    override fun getFavouriteById(id: Int): Single<Movie> = local.getFavouriteById(id)

    override fun getMovies(): LiveData<List<Movie>> = local.getMovies()


    override fun deleteMovie(movie: Movie) {
        local.deleteMovie(movie)
    }

    override fun insertMovie(movie: Movie) {
        local.insertMovie(movie)
    }

    override fun getMovies(page: Int): Single<MovieResponse> = remote.getMovies(page)
}
