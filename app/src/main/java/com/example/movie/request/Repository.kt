package com.example.movie.request

import com.example.movie.request.model.moviedetail.MovieDetailModel
import com.example.movie.request.model.genre.GenreResponse
import com.example.movie.request.model.movie.MovieSearchResponse
import retrofit2.Response

object Repository {

    suspend fun getPopularMovies(key: String, page:String):Response<MovieSearchResponse>{ return RetrofitInstance.api.getPopularMovies(key , page)}

    suspend fun getSearchedMovies(key: String, query: String, page:String): Response<MovieSearchResponse> {return RetrofitInstance.api.getSearchedMovies(key , query , page )}

    suspend fun getMovieAccordingToId(movie_id: Int, key: String):Response<MovieDetailModel> {return RetrofitInstance.api.getMovieAccordingToId(movie_id , key)}

    suspend fun getGenres(key: String):Response<GenreResponse>{return RetrofitInstance.api.getGenres(key)}

    suspend fun getMoviesOfTheGenre(key: String , genre_id:Int):Response<MovieSearchResponse>{return RetrofitInstance.api.getMoviesOfTheGenre(key , genre_id)}

}