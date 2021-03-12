package com.example.movie.response

import com.example.movie.request.model.MovieModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieSearchResponse(
    @SerializedName("total_results")
    @Expose()
    val totalCount: Int,
    @SerializedName("results")
    @Expose
    val movies: List<MovieModel>
)