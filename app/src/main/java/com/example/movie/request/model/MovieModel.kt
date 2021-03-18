package com.example.movie.request.model


data class MovieModel(
    val backdrop_path: String,
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Float,
)