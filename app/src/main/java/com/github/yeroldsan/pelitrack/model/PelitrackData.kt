package com.github.yeroldsan.pelitrack.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
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
data class SeriesResponse(
    @SerializedName("results") val series: List<Serie>
) {
    data class Serie(
        val id: Int,
        val name: String,
        val overview: String,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("first_air_date") val firstAirDate: String,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("vote_count") val voteCount: Int
    )
}
