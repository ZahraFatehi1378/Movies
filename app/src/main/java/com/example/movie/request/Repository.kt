package com.example.movie.request

import com.example.movie.request.model.MovieDetailModel
import com.example.movie.request.model.MovieModel
import com.example.movie.response.MovieSearchResponse
import retrofit2.Response

object Repository {

    suspend fun getPopularMovies(key: String, page:String):Response<MovieSearchResponse>{ return RetrofitInstance.api.getPopularMovies(key , page)}

    suspend fun getSearchedMovies(key: String, query: String, page:String): Response<MovieSearchResponse> {return RetrofitInstance.api.getSearchedMovies(key , query , page )}

    suspend fun getMovieAccordingToId(movie_id: Int, key: String):Response<MovieDetailModel> {return RetrofitInstance.api.getMovieAccordingToId(movie_id , key)}

  //  suspend fun getGenres(key: String):Response<GenreResponse>{}

}