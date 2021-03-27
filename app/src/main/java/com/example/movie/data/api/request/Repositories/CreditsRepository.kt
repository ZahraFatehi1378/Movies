package com.example.movie.data.api.request.Repositories

import com.example.movie.data.api.model.credits.CreditResponse
import com.example.movie.data.api.request.RetrofitInstance
import retrofit2.Response

class CreditsRepository {

    suspend fun getCredits(key: String , movie_id:Int): Response<CreditResponse> {
        return RetrofitInstance.api.getCredits(movie_id, key)
    }
}