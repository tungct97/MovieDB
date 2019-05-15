package com.example.moviedb.ui.screen.detail

import androidx.lifecycle.MutableLiveData
import com.example.moviedb.data.model.Movie
import com.example.moviedb.ui.base.BaseViewModel

class DetailViewModel : BaseViewModel() {
    val pathImage: MutableLiveData<String> = MutableLiveData()
    val releaseDate: MutableLiveData<String> = MutableLiveData()
    val voteCount: MutableLiveData<Int> = MutableLiveData()
    val overview: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()

    fun loadData(movie: Movie) {
        pathImage.value = movie.backdropPath
        releaseDate.value = movie.releaseDate
        voteCount.value = movie.voteCount
        overview.value = movie.overview
        title.value = movie.title
    }
}
