package com.example.movie.data.api.request.repositories

import android.util.Log
import com.example.movie.App
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.model.movie.MovieSearchResponse
import com.example.movie.data.api.request.RetrofitInstance
import com.example.movie.data.database.DataBase
import retrofit2.Response

class MovieListRepository {

    private val dbDao = DataBase.getDatabase().dataBaseDao()
    private val api = RetrofitInstance.api
    var result: List<MovieModel>? = null
    private lateinit var moviesList: Response<MovieSearchResponse>

    suspend fun getPopularMovies(key: String, page: String): List<MovieModel>? {

        if (App.isNetworkAvailable()) {
            moviesList = api.getPopularMovies(key, page)
            if (moviesList.code() == 200) {
                result = moviesList.body()?.movies
                dbDao.insertMovie(result!!)
            } else result = dbDao.getAllMovies()
        } else result = dbDao.getAllMovies()
        return result
    }

    //no need to be saved in db
    suspend fun getSearchedMovies(key: String, query: String, page: String): Response<MovieSearchResponse> {
        return RetrofitInstance.api.getSearchedMovies(key, query, page)
    }

    //no need to be saved in db
    suspend fun getMoviesOfTheGenre(key: String, genre_id: Int , page:String): Response<MovieSearchResponse> {
        return RetrofitInstance.api.getMoviesOfTheGenre(key, genre_id , page)
    }

    suspend fun getSavedMovies(): List<MovieModel> {
        Log.e("${dbDao.getSavedMovies().size}" , "###############33")
        return dbDao.getSavedMovies()
    }

}