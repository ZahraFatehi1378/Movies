package com.example.movie.data.api.request

import com.example.movie.data.api.util.Constant.Companion.BASE_URL
import com.example.movie.data.api.util.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api: MovieApi by lazy {
        retrofit.create(MovieApi :: class.java)
    }
}