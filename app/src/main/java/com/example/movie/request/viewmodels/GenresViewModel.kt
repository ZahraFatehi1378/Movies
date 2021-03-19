package com.example.movie.request.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.request.Repository
import com.example.movie.request.model.genre.GenreResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class GenresViewModel(var repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<GenreResponse>> = MutableLiveData()

    fun getGenres(key: String){
        viewModelScope.launch {
            val response = repository.getGenres( key )
            myResponse.value = response
        }
    }

}