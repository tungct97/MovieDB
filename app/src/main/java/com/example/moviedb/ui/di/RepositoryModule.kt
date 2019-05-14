package com.example.moviedb.ui.di

import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.repository.MovieRepository
import com.example.moviedb.data.source.MovieRemoteDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieDataSource.Remote> { MovieRemoteDataSource(get()) }

    single { MovieRepository(get()) }
}
