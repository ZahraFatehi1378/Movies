package com.example.movie.request.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.request.Repository
import com.example.movie.request.model.credits.CreditResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class CreditsViewModel(var repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<CreditResponse>> = MutableLiveData()

    fun getCredits(key: String, movie_id: Int) {
        viewModelScope.launch {
            val response = repository.getCredits(key, movie_id)
            myResponse.value = response
        }
    }
}