package com.example.movie.ui.interfaces

import com.example.movie.data.api.model.movie.MovieModel

interface OnRecyclerItemListener{

    fun onItemClicked(item: MovieModel?)
}