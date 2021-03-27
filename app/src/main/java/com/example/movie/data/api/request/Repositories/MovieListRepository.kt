package com.example.movie.data.api.request.Repositories

import com.example.movie.data.api.model.movie.MovieSearchResponse
import com.example.movie.data.api.request.RetrofitInstance
import retrofit2.Response

class MovieListRepository {

    suspend fun getPopularMovies(key: String, page: String): Response<MovieSearchResponse> {
        return RetrofitInstance.api.getPopularMovies(key, page)
    }

    suspend fun getSearchedMovies(key: String, query: String, page: String): Response<MovieSearchResponse> {
        return RetrofitInstance.api.getSearchedMovies(key, query, page)
    }

    suspend fun getMoviesOfTheGenre(key: String, genre_id: Int): Response<MovieSearchResponse> {
        return RetrofitInstance.api.getMoviesOfTheGenre(key, genre_id)
    }

}