package com.example.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("vote_count") private val voteCount: Int? = null,
    @SerializedName("id") private val id: Int,
    @SerializedName("video") private val video: Boolean? = null,
    @SerializedName("vote_average") private val voteAverage: Double? = null,
    @SerializedName("title") private val title: String? = null,
    @SerializedName("popularity") private val popularity: Double? = null,
    @SerializedName("poster_path") private val posterPath: String? = null,
    @SerializedName("original_language") private val originalLanguage: String? = null,
    @SerializedName("original_title") private val originalTitle: String? = null,
    @SerializedName("genre_ids") private val genreIds: List<Int>? = null,
    @SerializedName("backdrop_path") private val backdropPath: String? = null,
    @SerializedName("adult") private val adult: Boolean? = null,
    @SerializedName("overview") private val overview: String? = null,
    @SerializedName("release_date") private val releaseDate: String? = null
)
