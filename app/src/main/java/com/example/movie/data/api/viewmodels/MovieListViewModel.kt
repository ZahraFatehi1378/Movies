package com.example.movie.data.api.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.request.Repositories.MovieListRepository
import com.example.movie.data.api.util.Constant
import com.example.movie.data.api.viewmodels.paging.MoviesByGenrePagingSource
import com.example.movie.data.api.viewmodels.paging.PopularMoviePagingSource
import com.example.movie.data.api.viewmodels.paging.SearchedMoviePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieListViewModel : ViewModel() {

    val myResponse: MutableLiveData<PagingData<MovieModel>> = MutableLiveData()
    lateinit var moviePagingSource: Flow<PagingData<MovieModel>>
    val repository =  MovieListRepository()

//    val pager = Pager(
//        PagingConfig(pageSize = 50),
//        MovieRemoteMediator(1,database , RetrofitInstance.api)
//    )
//    {
//        database.dataBaseDao().movieById(1)
//    }


    fun setPopularMovies() {
        moviePagingSource = Pager(PagingConfig(pageSize = 20)) {
            PopularMoviePagingSource(Constant.API_KEY, repository)
        }.flow.cachedIn(viewModelScope)
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
        viewModelScope.launch {
            try {
                moviePagingSource.collectLatest {
                    myResponse.postValue(it)
                }

            } catch (e: Exception) {
                Log.e("TAG", "fetchContents: ${e.message}")
            }
        }
    }

}