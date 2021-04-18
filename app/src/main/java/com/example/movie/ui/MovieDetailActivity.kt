package com.example.movie.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat.setBackgroundTintList
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.movie.R
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.data.api.model.credits.CastModel
import com.example.movie.data.api.model.credits.CrewModel
import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.data.api.model.moviedetail.MovieDetailModel
import com.example.movie.data.api.util.Constant
import com.example.movie.data.api.viewmodels.CreditsViewModel
import com.example.movie.data.api.viewmodels.MovieDetailViewModel
import com.example.movie.data.api.viewmodels.factories.CreditsViewModelFactory
import com.example.movie.data.api.viewmodels.factories.MovieDetailsViewModelFactory
import com.example.movie.data.database.DataBase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var movieCreditsViewModel: CreditsViewModel
    private lateinit var currentMovieDetail: MovieDetailModel

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

//        if (currentMovieDetail.isSaved) {
//            binding.saveBtn.setColorFilter(ContextCompat.getColor(baseContext, R.color.more_dark_blue))
//        } else {
//            binding.saveBtn.setColorFilter(ContextCompat.getColor(baseContext, R.color.yellow))
//        }

        binding.saveBtn.setOnClickListener {
                savedClicked(it as FloatingActionButton)
        }
    }

    private fun getMovieDetail(binding: ActivityMovieDetailBinding) {
        movieDetailViewModel.getMovieDetails(Constant.API_KEY, intent.getIntExtra("movie_id", 0))
        movieDetailViewModel.myResponse.observe(this, { response ->
            if (response.id == -1) {
                //todo
            } else {
                this.currentMovieDetail = response
                binding.chosenMovie = response
                binding.genresList = setList(response.genres!!)
                getCredits(binding, response!!.id)
                if (currentMovieDetail.isSaved) {
                    binding.saveBtn.setColorFilter(ContextCompat.getColor(baseContext, R.color.yellow))
                } else {
                    binding.saveBtn.setColorFilter(ContextCompat.getColor(baseContext, R.color.more_dark_blue))
                }
            }
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
                result.append("name : ${crew.name} \t")
                result.append("job : ${crew.job} \n")
            }
        }
        return result.toString()
    }

    private fun setCasts(casts: List<CastModel>?): String? {
        val result = StringBuilder()
        if (casts != null) {
            for (cast: CastModel in casts) {
                result.append("name : ${cast.name} \t")
                result.append("character : ${cast.character} \n")
            }
        }
        return result.toString()
    }

    private fun setList(body: List<GenreModel>): String {
        val result = StringBuilder()

        if (body.isNotEmpty())
        for (genre: GenreModel in body) {
            result.append("- ${genre.name}")
        }
        else{
            result.append("--")
        }

        return result.substring(1).toString()
    }

    private fun savedClicked(view: FloatingActionButton) {
        CoroutineScope(IO).launch {
            if (currentMovieDetail.isSaved) {
                view.setColorFilter(ContextCompat.getColor(baseContext, R.color.more_dark_blue))
                currentMovieDetail.isSaved = false
            } else {
                view.setColorFilter(ContextCompat.getColor(baseContext, R.color.yellow))
                currentMovieDetail.isSaved = true
            }
            DataBase.getDatabase().dataBaseDao().insertMovieDetail(currentMovieDetail)
        }
    }
}