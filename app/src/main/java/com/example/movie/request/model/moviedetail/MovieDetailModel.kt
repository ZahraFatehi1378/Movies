package com.example.movie.request.model.moviedetail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movie.request.model.genre.GenreModel

class MovieDetailModel(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val title: String,
    val genres: List<GenreModel>,
    val popularity: Float,
    val vote_average: Float,
    val tagline: String
) {

    //for dataBinding in glide


    companion object {
        @JvmStatic
        @BindingAdapter("imageBG")
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        }

//            @BindingAdapter("genres")
//    fun loadGenres(view: TextView , a :String) {
//        view.text =(a)
////            for (genre in genres){
////                view.text=(genre.name)
////            }
//    }

    }
}