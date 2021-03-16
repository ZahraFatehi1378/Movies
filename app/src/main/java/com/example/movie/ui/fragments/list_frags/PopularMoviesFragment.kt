package com.example.movie.ui.fragments.list_frags

import androidx.lifecycle.Observer
import com.example.movie.request.util.Constant
import com.example.movie.response.ResultsLists


class PopularMoviesFragment : ListsBaseFragment() {


    override fun setMovies() {
        ResultsLists.popularPageNumber++
        setPopularMovies(ResultsLists.popularPageNumber.toString())
    }

    private fun setPopularMovies(page: String) {
        movieListViewModel.getPopularMovies(Constant.API_KEY, page)
        movieListViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.code() == 200) {
                if (page == "1") ResultsLists.popularMovies = response.body()?.movies!!
                else ResultsLists.popularMovies.toMutableList().addAll(response.body()?.movies!!)
                ResultsLists.recyclerList = ResultsLists.popularMovies
                setMoviesInRecycler()
            } else println(response.errorBody().toString())
        })
    }

}