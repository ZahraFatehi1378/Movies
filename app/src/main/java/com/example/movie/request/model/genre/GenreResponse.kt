package com.example.movie.request.model.genre

import com.example.movie.request.model.genre.GenreModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenreResponse (
    @SerializedName("genres")
    @Expose
    val genres: List<GenreModel>
)
