package com.example.movie.data.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.data.api.model.genre.GenreResponse
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.request.repositories.GenresRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class GenresViewModel: ViewModel() {

     val myResponse: MutableLiveData<List<GenreModel>> = MutableLiveData()
    private val repository = GenresRepository()

    fun getGenres(key: String){
        viewModelScope.launch {
            val response = repository.getGenres( key )
            myResponse.value = response
        }
    }

}