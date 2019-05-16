package com.example.moviedb.data.source.sql

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedb.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}
