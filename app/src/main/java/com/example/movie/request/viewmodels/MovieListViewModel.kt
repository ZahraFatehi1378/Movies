package com.example.movie.request.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.request.Repository
import com.example.movie.request.model.movie.MovieSearchResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieListViewModel(var repository:Repository) :ViewModel()
{

    val myResponse:MutableLiveData<Response<MovieSearchResponse>> = MutableLiveData()

    fun getSearchedMovies(key: String, query: String, page:String){
        viewModelScope.launch {
            val response = repository.getSearchedMovies( key , query , page)
            myResponse.value = response
        }
    }

    fun getPopularMovies(key: String, page:String){
        viewModelScope.launch {
            val response = repository.getPopularMovies( key , page)
            println(response.body()?.movies)
            myResponse.value = response
        }
    }

    fun getMoviesOfTheGenre(key: String , genre_id:Int){
        viewModelScope.launch {
            val response = repository.getMoviesOfTheGenre( key , genre_id)
            myResponse.value = response
        }
    }

}