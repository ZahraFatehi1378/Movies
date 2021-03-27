package com.example.movie.data.api.request.Repositories

import com.example.movie.data.api.model.genre.GenreResponse
import com.example.movie.data.api.request.RetrofitInstance
import retrofit2.Response

class GenresRepository {
    suspend fun getGenres(key: String): Response<GenreResponse> {
        return RetrofitInstance.api.getGenres(key)
    }
}