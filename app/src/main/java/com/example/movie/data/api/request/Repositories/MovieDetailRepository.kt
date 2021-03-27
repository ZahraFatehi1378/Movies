package com.example.movie.data.api.request.Repositories

import com.example.movie.data.api.model.moviedetail.MovieDetailModel
import com.example.movie.data.api.request.RetrofitInstance
import com.example.movie.data.database.DataBase
import retrofit2.Response

class MovieDetailRepository {


    suspend fun getMovieAccordingToId(movie_id: Int, key: String): Response<MovieDetailModel> {
        DataBase.getDatabase()
        return RetrofitInstance.api.getMovieAccordingToId(movie_id, key)
    }
}