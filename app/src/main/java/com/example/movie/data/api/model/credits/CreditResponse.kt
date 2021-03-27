package com.example.movie.data.api.model.credits

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreditResponse
    (
    @SerializedName("cast")
    @Expose
    val cast: List<CastModel>,
    @SerializedName("crew")
    @Expose
    val crew: List<CrewModel>
)