package com.example.moviedb.ui.di

import com.example.moviedb.ui.screen.MovieViewModel
import com.example.moviedb.ui.screen.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel() }
}
