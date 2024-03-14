package com.github.yeroldsan.pelitrack.model

import com.google.gson.annotations.SerializedName

data class PelitrackData(
    @SerializedName("results") val movies: List<Movie>,
) {

    data class Movie(
        val id: Int,
        val title: String,
        val overview: String,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("vote_count") val voteCount: Int
    )
}
