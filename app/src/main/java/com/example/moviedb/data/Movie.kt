package com.example.moviedb.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("page") private val page: Int? = null,
    @SerializedName("total_results") private val totalResults: Int? = null,
    @SerializedName("total_pages") private val totalPages: Int? = null,
    @SerializedName("results") private val results: List<Result>? = null
)
