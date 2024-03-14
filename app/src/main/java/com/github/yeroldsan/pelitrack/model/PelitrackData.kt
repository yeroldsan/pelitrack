package com.github.yeroldsan.pelitrack.model

data class PelitrackData(
    val movies: List<Movie>,
    val series: List<Serie>
) {

    data class Movie(
        val id: Int,
        val title: String,
        val overview: String,
        val posterPath: String,
        val backdropPath: String,
        val releaseDate: String,
        val voteAverage: Double,
        val voteCount: Int,
        val isFavorite: Boolean,
        val isWatched: Boolean,
        val genreIds: List<Int>,
    )

    data class Serie(
        val id: Int,
        val name: String,
        val overview: String,
        val posterPath: String,
        val backdropPath: String,
        val voteAverage: Double,
        val voteCount: Int,
        val isFavorite: Boolean,
        val isWatched: Boolean,
        val genreIds: List<Int>,
    )
}
