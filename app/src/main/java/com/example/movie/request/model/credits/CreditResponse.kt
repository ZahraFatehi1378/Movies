package com.example.movie.request.model.credits

import com.example.movie.request.model.movie.MovieModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreditResponse
    (
    @SerializedName("cast")
    @Expose
    val cast: Int,
    @SerializedName("crew")
    @Expose
    val crew: List<MovieModel>
)