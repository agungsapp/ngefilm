package com.mobile.ngefilm

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val vote_average: Float,
    var video_url: String? = null, // Placeholder untuk trailer
    var isFavorite: Boolean = false
)