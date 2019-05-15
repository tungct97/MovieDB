package com.example.moviedb.ui.screen.detail

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.data.model.Movie
import com.example.moviedb.ui.base.BaseViewModel

class DetailViewModel : BaseViewModel() {
    var movies: MutableLiveData<Movie> = MutableLiveData()

    fun loadData(movie: Movie) {
        movies.value = movie
    }
}
