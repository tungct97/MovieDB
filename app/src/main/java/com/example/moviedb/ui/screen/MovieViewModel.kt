package com.example.moviedb.ui.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.MovieRepository
import com.example.moviedb.ui.base.BaseViewModel
import com.example.moviedb.ui.base.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel(private val repository: MovieRepository) : BaseViewModel() {
    val movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val loadmore = MutableLiveData<Boolean>().apply { value = false }
    val fistLoading = MutableLiveData<Boolean>().apply { value = false }
    val message = SingleLiveEvent<Boolean>()
    var page: Int = 1

    fun getMovies() {
        page = 1
        fistLoading.value = true
        lanchDisposable(
            repository.getMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate({
                    fistLoading.value = false
                })
                .subscribe({
                    page++
                    movies.value = it.results?.toMutableList()
                }, {
                    message.value = true
                })
        )
    }


    fun refreshLayout() {
        lanchDisposable(
            repository.getMovies(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess {
                    page = 1
                    page++
                }.subscribe({
                    movies.value = it.results?.toMutableList()
                }, {
                    message.value = true
                })
        )
    }

    fun reloadingMovie() {
        loadmore.value = true
        lanchDisposable(
            repository.getMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate({
                    loadmore.value = false
                })
                .subscribe({
                    updateMovies(it.results!!.toMutableList())
                    page++
                }, {
                    message.value = true
                })
        )
    }

    fun updateMovies(movies: MutableList<Movie>) {
        if (this.movies.value == null) {
            this.movies.value = movies
        } else {
            val moviesSub: MutableList<Movie> = this.movies.value!!
            moviesSub.addAll(movies)
            this.movies.value = moviesSub
        }
    }
}
