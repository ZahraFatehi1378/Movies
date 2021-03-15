package com.example.movie.request

import androidx.lifecycle.MutableLiveData
import com.example.movie.request.RetrofitInstance
import com.example.movie.request.model.MovieModel
import com.example.movie.response.MovieResponse
import com.example.movie.response.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.Query

object Repository {

    suspend fun getPopularMovies(key: String, page:String):Response<MovieSearchResponse>{ return RetrofitInstance.api.getPopularMovies(key , page)}

    suspend fun getSearchedMovies(key: String, query: String, page:String): Response<MovieSearchResponse> {return RetrofitInstance.api.getSearchedMovies(key , query , page )}

    suspend fun getMovieAccordingToId(movie_id: Int, key: String):Response<MovieResponse> {return RetrofitInstance.api.getMovieAccordingToId(movie_id , key)}

}