package com.example.movie.request.model

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieModel(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Float
):Parcelable{


    companion object {

        @JvmStatic
        @BindingAdapter("imageBG")
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.getContext())
                .load(imageUrl)
                .into(view)
        }
    }

}