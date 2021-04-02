package com.example.movie.ui.fragments.list_frags

import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class PopularMoviesFragment : BaseFragment() {


    override fun setMovies() {
        setPopularMovies()
    }

    private fun setPopularMovies() {
        movieListViewModel.getPopularMovies()
    //    movieListViewModel.getMovies()

        movieListViewModel.myResponse.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                Log.e("*******" , "${it}")
                moviesAdaptor?.submitData(it)
            }
        })
//        lifecycleScope.launch {
//            movieListViewModel.fetchMovies().collectLatest {
//                moviesAdaptor?.submitData(it)
//            }
//        }
    }


}