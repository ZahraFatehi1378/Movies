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
import com.example.movie.response.ResultsLists
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {


    private lateinit var movieDetailModel: MovieDetailModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

    }





//    private fun setMovieDetails(id: Int) {
//        movieDetailModel.getMovieAccordingToId(id, API_KEY)
//        movieDetailModel.myResponse.observe(this, Observer { response ->
//            if (response.code() == 200) {
//                print(response.body()?.movie)
//
//            } else {
//            }
//        })
//
//    }

}