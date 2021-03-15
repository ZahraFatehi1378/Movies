package com.example.movie.response

import com.example.movie.request.model.MovieModel

object ResultsLists {
     var searchedMovies: List<MovieModel> = emptyList()
     var popularMovies: List<MovieModel> = emptyList()
    lateinit var movieDetail: MovieModel
}