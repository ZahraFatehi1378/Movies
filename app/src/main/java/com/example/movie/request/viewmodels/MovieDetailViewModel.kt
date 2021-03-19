package com.example.movie.request.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.request.Repository
import com.example.movie.request.model.moviedetail.MovieDetailModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailViewModel (var repository:Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<MovieDetailModel>> = MutableLiveData()


    fun getMovieDetails(key: String,id:Int){
        viewModelScope.launch {
            val response = repository.getMovieAccordingToId(id , key)
            myResponse.value = response
        }
    }
}