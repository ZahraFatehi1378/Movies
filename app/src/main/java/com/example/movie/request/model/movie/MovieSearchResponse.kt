package com.example.movie.request.model.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("total_results")
    @Expose()
    val totalCount: Int,
    @SerializedName("results")
    @Expose
    val movies: List<MovieModel>
)