package com.example.movie.ui.fragments.list_frags

import androidx.lifecycle.lifecycleScope
import com.example.movie.request.util.Constant
import kotlinx.coroutines.launch


class PopularMoviesFragment : BaseFragment() {


    override fun setMovies() {
        setPopularMovies()
    }

    private fun setPopularMovies() {
        movieListViewModel.setPopularMovies()
        movieListViewModel.getMovies()
        movieListViewModel.myResponse.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                moviesAdaptor?.submitData(it)
            }
        })
    }


}