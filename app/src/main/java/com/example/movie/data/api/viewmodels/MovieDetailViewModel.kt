package com.example.movie.data.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.api.model.moviedetail.MovieDetailModel
import com.example.movie.data.api.request.repositories.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    val myResponse: MutableLiveData<MovieDetailModel> = MutableLiveData()
    private val repository= MovieDetailRepository()

    fun getMovieDetails(key: String,id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMovieAccordingToId(id , key)
            myResponse.postValue(response)
        }
    }
}