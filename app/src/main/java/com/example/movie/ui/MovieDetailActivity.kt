package com.example.movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.movie.R
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.request.Repository
import com.example.movie.request.model.GenreModel
import com.example.movie.request.util.Constant
import com.example.movie.request.viewmodels.MovieDetailViewModel
import com.example.movie.request.viewmodels.MovieDetailsViewModelFactory
import java.lang.StringBuilder

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var binding: ActivityMovieDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        val viewModelFactory = MovieDetailsViewModelFactory(Repository)
        movieDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        movieDetailViewModel.getMovieDetails(Constant.API_KEY, intent.getIntExtra("movie_id", 0))
        movieDetailViewModel.myResponse.observe(this, { response ->
            if (response.code() == 200) {
                binding!!.chosenMovie = response.body()
                println(response.body()?.genres)
                binding.genresList = setList(response.body()?.genres!!)
            } else println(response.errorBody().toString())
        })
    }

    private fun setList(body: List<GenreModel>): String? {
        var result = StringBuilder()
        for (genre: GenreModel in body) {
            result.append("- ${genre.name}")
        }
        return result.substring(1).toString()
    }
}