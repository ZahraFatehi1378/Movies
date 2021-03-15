package com.example.movie.request.util

import com.example.movie.request.model.MovieModel
import com.example.movie.response.MovieResponse
import com.example.movie.response.MovieSearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    // https://api.themoviedb.org/3/search/movie?api_key=95c73f18955a62900bc3a92fce1bfd72&query=Jack+Reacher

    // populars
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String ,
        @Query("page") page:String
    ): Response<MovieSearchResponse>

    //search with name
    @GET("/3/search/movie")
    suspend fun getSearchedMovies(
        @Query("api_key") key: String ,
        @Query("query") query: String,
        @Query("page") page:String
    ): Response<MovieSearchResponse>


    //search with id
    @GET("/3/movie/{movie_id}?")
     suspend fun getMovieAccordingToId(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") key: String
    ): Response<MovieResponse>
}