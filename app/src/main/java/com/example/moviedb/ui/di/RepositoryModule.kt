package com.example.moviedb.ui.di

import android.content.Context
import androidx.room.Room
import com.example.moviedb.data.MovieDataSource
import com.example.moviedb.data.repository.MovieRepository
import com.example.moviedb.data.source.MovieLocalDataSource
import com.example.moviedb.data.source.MovieRemoteDataSource
import com.example.moviedb.data.source.sql.MovieDataBase
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieDataSource.Remote> { MovieRemoteDataSource(get()) }

    single<MovieDataSource.Local> { MovieLocalDataSource(get()) }

    single { MovieRepository(get(), get()) }

    single { createAppDatabase(get(), get()) }

    single { createDatabaseName() }

    single { createMovieDao(get()) }
}

fun createDatabaseName() = "moviedbdatabase"

fun createAppDatabase(dbName: String, context: Context) =
    Room.databaseBuilder(context, MovieDataBase::class.java, dbName).build()

fun createMovieDao(appDatabase: MovieDataBase) = appDatabase.movieDao()
