package com.example.movie.ui.fragments.list_frags

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class PopularMoviesFragment : BaseFragment() {


    override fun setMovies() {
        setPopularMovies()
    }

    private fun setPopularMovies() {
        movieListViewModel.getPopularMovies()

        movieListViewModel.myResponse.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                moviesAdaptor?.submitData(it)
            }
        })
    }
}