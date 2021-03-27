package com.example.movie.data.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.api.model.moviedetail.MovieDetailModel
import com.example.movie.data.api.request.Repositories.MovieDetailRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {

    val myResponse: MutableLiveData<Response<MovieDetailModel>> = MutableLiveData()
    val repository= MovieDetailRepository()

    fun getMovieDetails(key: String,id:Int){
        viewModelScope.launch {
            val response = repository.getMovieAccordingToId(id , key)
            myResponse.value = response
        }
    }
}