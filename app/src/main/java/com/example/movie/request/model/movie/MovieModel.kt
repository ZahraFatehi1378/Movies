package com.example.movie.request.model.movie


data class MovieModel(
    val id: Int,
    val poster_path: String,
    val title: String,
    val release_date: String,
    val vote_average: Float,
    val original_language:String
)