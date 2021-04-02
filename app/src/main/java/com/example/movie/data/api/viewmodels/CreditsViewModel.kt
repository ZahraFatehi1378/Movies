package com.example.movie.data.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.api.model.credits.CreditResponse
import com.example.movie.data.api.request.repositories.CreditsRepository
import kotlinx.coroutines.launch

class CreditsViewModel : ViewModel() {

    val repository = CreditsRepository()
    val myResponse: MutableLiveData<CreditResponse> = MutableLiveData()

    fun getCredits(key: String, movie_id: Int) {
        viewModelScope.launch {
            val response = repository.getCredits(key, movie_id)
            myResponse.value = response
        }
    }
}