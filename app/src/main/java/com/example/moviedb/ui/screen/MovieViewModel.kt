package com.example.moviedb.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.data.model.MovieResponse
import com.example.moviedb.data.repository.MovieRepository
import com.example.moviedb.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel(private val repository: MovieRepository) : BaseViewModel() {
    private val data: MutableLiveData<MovieResponse> by lazy {
        MutableLiveData<MovieResponse>()
    }

    fun getMovies(page: Int): LiveData<MovieResponse> {
        lanchDisposable {
            repository.getMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, {
                    data.value = null
                })
        }
        return data
    }
}
