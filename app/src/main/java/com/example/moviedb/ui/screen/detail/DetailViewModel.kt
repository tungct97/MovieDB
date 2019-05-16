package com.example.moviedb.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.MovieRepository
import com.example.moviedb.ui.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(val repository: MovieRepository) : BaseViewModel() {
    var movies: MutableLiveData<Movie> = MutableLiveData()
    var favourite: MutableLiveData<Boolean> = MutableLiveData()

    fun loadData(movie: Movie) {
        movies.value = movie
        getFavourite(movie.id)
    }

    fun getFavourite(id: Int) {
        val disposable =
            repository.getFavouriteById(id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe({
                    if (it != null) favourite.value = true
                    else favourite.value = false
                }, {
                    favourite.value = false
                })
        lanchDisposable(disposable)
    }

    fun insertMovie(movie: Movie) {
        val disposable =
            repository.insertMovie(movie)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(
                    Schedulers.io()
                ).subscribe({
                    favourite.value = true

                }, {
                    favourite.value = false
                })
        lanchDisposable(disposable)
    }

    fun deleteMovie(movie: Movie) {
        val disposable = repository.deleteMovie(movie)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(
                Schedulers.io()
            ).subscribe({
                favourite.value = false
            }, {
                favourite.value = true
            })
        lanchDisposable(disposable)
    }

    fun getMovies(): LiveData<List<Movie>> {
        return repository.getMovies()
    }

    fun onClickFavourite() {
        if (favourite.value == false) {
            insertMovie(movies.value!!)
        } else {
            deleteMovie(movies.value!!)
        }
    }
}
