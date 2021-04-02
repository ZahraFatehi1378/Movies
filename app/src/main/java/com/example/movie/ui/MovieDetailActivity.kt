package com.example.movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.movie.R
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.data.api.model.credits.CastModel
import com.example.movie.data.api.model.credits.CrewModel
import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.data.api.util.Constant
import com.example.movie.data.api.viewmodels.CreditsViewModel
import com.example.movie.data.api.viewmodels.MovieDetailViewModel
import com.example.movie.data.api.viewmodels.factories.CreditsViewModelFactory
import com.example.movie.data.api.viewmodels.factories.MovieDetailsViewModelFactory
import java.lang.StringBuilder

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var movieCreditsViewModel: CreditsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {

        val binding: ActivityMovieDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        val movieDetailViewModelFactory = MovieDetailsViewModelFactory()
        val movieCreditsViewModelFactory = CreditsViewModelFactory()

        movieDetailViewModel = ViewModelProvider(
            this,
            movieDetailViewModelFactory
        ).get(MovieDetailViewModel::class.java)
        movieCreditsViewModel =
            ViewModelProvider(this, movieCreditsViewModelFactory).get(CreditsViewModel::class.java)

        getMovieDetail(binding)
    }

    private fun getMovieDetail(binding: ActivityMovieDetailBinding) {
        movieDetailViewModel.getMovieDetails(Constant.API_KEY, intent.getIntExtra("movie_id", 0))
        movieDetailViewModel.myResponse.observe(this, { response ->
            binding.chosenMovie = response
            binding.genresList = setList(response.genres!!)
            getCredits(binding, response!!.id)
        })
    }

    private fun getCredits(binding: ActivityMovieDetailBinding, id: Int) {
        movieCreditsViewModel.getCredits(Constant.API_KEY, id)
        println(id)
        movieCreditsViewModel.myResponse.observe(this, { response ->
            binding.crews = setCrews(response.crew)
            binding.casts = setCasts(response.cast)

        })
    }

    private fun setCrews(crews: List<CrewModel>?): String? {
        val result = StringBuilder()
        if (crews != null) {
            for (crew: CrewModel in crews) {
                result.append("name : ${crew.name} \n")
                result.append("job : ${crew.job} \n")
            }
        }
        return result.toString()
    }

    private fun setCasts(casts: List<CastModel>?): String? {
        val result = StringBuilder()
        if (casts != null) {
            for (cast: CastModel in casts) {
                result.append("name : ${cast.name} \n")
                result.append("character : ${cast.character} \n")
            }
        }
        return result.toString()
    }

    private fun setList(body: List<GenreModel>): String {
        val result = StringBuilder()
        for (genre: GenreModel in body) {
            result.append("- ${genre.name}")
        }
        return result.substring(1).toString()
    }
}