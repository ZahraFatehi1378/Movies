package com.example.movie.data.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.request.repositories.MovieListRepository
import com.example.movie.data.api.util.Constant
import com.example.movie.data.api.viewmodels.paging.MoviesByGenrePagingSource
import com.example.movie.data.api.viewmodels.paging.PopularMoviePagingSource
import com.example.movie.data.api.viewmodels.paging.SearchedMoviePagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    val myResponse: MutableLiveData<PagingData<MovieModel>> = MutableLiveData()
    val mySavedMoviesResponse :MutableLiveData<List<MovieModel>> = MutableLiveData()
    private lateinit var moviePagingSource: Flow<PagingData<MovieModel>>
    private val repository = MovieListRepository()


    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            Pager(PagingConfig(pageSize = 20)) {
                PopularMoviePagingSource(Constant.API_KEY, repository)
            }.flow.cachedIn(viewModelScope).collectLatest {
                myResponse.postValue(it)
            }
        }
    }

    fun setSearchedMovies(query: String) {
        moviePagingSource = Pager(PagingConfig(20)) {
            SearchedMoviePagingSource(Constant.API_KEY, query, repository)
        }.flow.cachedIn(viewModelScope)
    }


    fun setMoviesByGenre(genre_id: Int) {
        moviePagingSource = Pager(PagingConfig(20)) {
            MoviesByGenrePagingSource(Constant.API_KEY, genre_id, repository)
        }.flow.cachedIn(viewModelScope)
    }


    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviePagingSource.collectLatest {
                myResponse.postValue(it)
            }
        }
    }

    fun getSavedMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            mySavedMoviesResponse.postValue(repository.getSavedMovies())
        }
    }

}