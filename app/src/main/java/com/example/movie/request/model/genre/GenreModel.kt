package com.example.movie.request.model.genre

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreModel(
    val id: Int,
    val name: String
):Parcelable