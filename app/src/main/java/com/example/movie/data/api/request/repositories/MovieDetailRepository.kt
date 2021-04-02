package com.example.movie.data.api.request.repositories

import com.example.movie.data.api.model.moviedetail.MovieDetailModel
import com.example.movie.data.api.request.RetrofitInstance
import com.example.movie.data.database.DataBase
import retrofit2.Response

class MovieDetailRepository {

    private val dbDao = DataBase.getDatabase().dataBaseDao()
    private val api = RetrofitInstance.api
    var result: MovieDetailModel? = null
    private lateinit var movieDetail: Response<MovieDetailModel>

    suspend fun getMovieAccordingToId(movie_id: Int, key: String): MovieDetailModel {
        result = dbDao.getMovieDetail(movie_id)
        if (result == null) {
            movieDetail = api.getMovieAccordingToId(movie_id, key)
            if (movieDetail.code() == 200) {
                result = movieDetail.body()
                dbDao.insertMovieDetail(result!!)
            } else println(movieDetail.errorBody().toString())
        }
        return result as MovieDetailModel
    }
}