package com.example.movie.request.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.request.Repository
import com.example.movie.request.model.MovieModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailModel (var repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<MovieModel>> = MutableLiveData()

    fun getMovieAccordingToId(movie_id: Int, key: String){
        viewModelScope.launch {
            myResponse.value = repository.getMovieAccordingToId(movie_id , key )
        }
    }
}