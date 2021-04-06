package com.example.movie.data.api.util

import com.example.movie.data.api.model.credits.CreditResponse
import com.example.movie.data.api.model.moviedetail.MovieDetailModel
import com.example.movie.data.api.model.genre.GenreResponse
import com.example.movie.data.api.model.movie.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    // populars
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): Response<MovieSearchResponse>

    //search with name
    @GET("/3/search/movie")
    suspend fun getSearchedMovies(
        @Query("api_key") key: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): Response<MovieSearchResponse>

    //search with id
    @GET("/3/movie/{movie_id}?")
    suspend fun getMovieAccordingToId(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") key: String
    ): Response<MovieDetailModel>

    //genres
    @GET("/3/genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String
    ): Response<GenreResponse>

    //credits
    @GET("/3/movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") key: String
    ): Response<CreditResponse>

    //movies according the genre
    @GET("/3/discover/movie")
    suspend fun getMoviesOfTheGenre(
        @Query("api_key") key: String,
        @Query("with_genres") genre: Int,
        @Query("page") page: String
    ): Response<MovieSearchResponse>
}