package com.example.movie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.movie.R
import com.example.movie.request.util.Constant
import com.example.movie.response.ResultsLists
import com.example.movie.ui.adaptor.MoviesAdaptor


class SearchedMoviesListFragment : BaseFragment() {

    private fun setSearchedMovies(page: String, query: String) {
        movieListViewModel.getMovies(Constant.API_KEY, query, page)
        movieListViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.code() == 200) {
                if (page == "1") ResultsLists.searchedMovies = response.body()?.movies!!
                else ResultsLists.searchedMovies.toMutableList().addAll(response.body()?.movies!!)
                setMoviesInRecycler()
            } else println(response.errorBody().toString())
        })
    }

    override fun setMovies() {
        setSearchedMovies("1", "flash")
    }


    override fun setMoviesInRecycler() {
        //todo ask why
        //  moviesAdaptor?.notifyDataSetChanged()

        moviesAdaptor = MoviesAdaptor(ResultsLists.popularMovies)
        recyclerView?.adapter = moviesAdaptor
    }

}