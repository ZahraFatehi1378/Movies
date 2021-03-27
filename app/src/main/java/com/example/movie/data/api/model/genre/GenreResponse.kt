package com.example.movie.data.api.model.genre

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenreResponse (
    @SerializedName("genres")
    @Expose
    val genres: List<GenreModel>
)
