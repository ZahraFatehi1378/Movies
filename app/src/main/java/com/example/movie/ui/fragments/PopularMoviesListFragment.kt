package com.example.movie.ui.fragments

import androidx.lifecycle.Observer
import com.example.movie.request.util.Constant
import com.example.movie.response.ResultsLists
import com.example.movie.ui.adaptor.MoviesAdaptor


class PopularMoviesListFragment : BaseFragment() {

    private fun setPopularMovies(page: String) {
        movieListViewModel.getPopularMovies(Constant.API_KEY, page)
        movieListViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.code() == 200) {
                if (page == "1") ResultsLists.popularMovies = response.body()?.movies!!
                else ResultsLists.popularMovies.toMutableList().addAll(response.body()?.movies!!)
                setMoviesInRecycler()
            } else println(response.errorBody().toString())
        })
    }


    override fun setMoviesInRecycler() {
        //todo ask why
        //  moviesAdaptor?.notifyDataSetChanged()

        moviesAdaptor = MoviesAdaptor(ResultsLists.popularMovies)
        recyclerView?.adapter = moviesAdaptor
    }


    override fun setMovies() {
        setPopularMovies("1")
    }



}