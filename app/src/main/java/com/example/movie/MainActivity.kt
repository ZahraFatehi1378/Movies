package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movie.request.Repository
import com.example.movie.request.util.Constant.Companion.API_KEY
import com.example.movie.request.viewmodels.MovieDetailModel
import com.example.movie.request.viewmodels.MovieListViewModel
import com.example.movie.request.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var repository: Repository
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var movieDetailModel: MovieDetailModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        movieListViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
        getSearchedMovies()
    }

    private fun getPopularMovies() {
        movieListViewModel.getPopularMovies(API_KEY, "1")
        movieListViewModel.myResponse.observe(this, Observer { response ->
            if (response.code() == 200) {
                println("++++++++")
                print(response.body()?.movies)
            } else {
                println("error")
            }
        })
    }

    private fun getSearchedMovies() {
        movieListViewModel.getMovies(API_KEY, "Jack Reacher", "1")
        movieListViewModel.myResponse.observe(this, Observer { response ->
            if (response.code() == 200) {
                println("++++++++++++++++++++++++++++++++")
                println(response.body()?.movies)
            } else print("-------------------------------")
            println("++++++++++++========================")
        })
    }

    private fun getMovieDetails(id: Int) {
        movieDetailModel.getMovieAccordingToId(id, API_KEY)
        movieDetailModel.myResponse.observe(this, Observer { response ->
            if (response.code() == 200) {
            } else {
            }
        })

    }

}