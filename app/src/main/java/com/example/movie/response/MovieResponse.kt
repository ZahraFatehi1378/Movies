package com.example.movie.response

import com.example.movie.request.model.MovieModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("results")
    @Expose
    val movie: MovieModel
)