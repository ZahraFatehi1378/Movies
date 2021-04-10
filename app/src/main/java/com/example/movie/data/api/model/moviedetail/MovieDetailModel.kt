package com.example.movie.data.api.model.moviedetail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.example.movie.data.api.model.genre.GenreModel

@Entity(tableName = "movie_detail")
class MovieDetailModel(

    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "original_title")
    val original_title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "genres")
    val genres: List<GenreModel>,
    @ColumnInfo(name = "popularity")
    val popularity: Float,
    @ColumnInfo(name = "vote_average")
    val vote_average: Float,
    @ColumnInfo(name = "tagline")
    val tagline: String,
    @ColumnInfo(name = "isSaved")
    var isSaved:Boolean = false
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
    }
}