package com.example.movie.response

import com.example.movie.request.model.MovieModel

object ResultsLists {
    var searchedMovies: List<MovieModel> = emptyList()
    var popularMovies: List<MovieModel> = emptyList()

    var popularPageNumber = 0
    var searchedMoviesPageNumber = 0
    lateinit var movieDetail: MovieModel
}