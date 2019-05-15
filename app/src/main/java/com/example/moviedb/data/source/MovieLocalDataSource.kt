package com.example.moviedb.data.source

import androidx.lifecycle.LiveData
import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.source.sql.MovieDAO
import io.reactivex.Single

class MovieLocalDataSource(val movieDao: MovieDAO) : MovieDataSource.Local {
    override fun getFavouriteById(id: Int): Single<Movie> = movieDao.getFavouriteById(id)

    override fun getMovies(): LiveData<List<Movie>> = movieDao.getMovies()

    override fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    override fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }
}
