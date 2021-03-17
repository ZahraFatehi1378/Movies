package com.example.movie.response

import com.example.movie.request.model.MovieModel

object ResultsLists {

    var recyclerList = mutableListOf<MovieModel>()

    var popularPageNumber = 0
    var searchedMoviesPageNumber = 0
    lateinit var movieDetail: MovieModel
}