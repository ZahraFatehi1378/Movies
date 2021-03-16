package com.example.movie.ui.fragments.list_frags


import androidx.lifecycle.Observer
import com.example.movie.request.util.Constant
import com.example.movie.response.ResultsLists


class SearchedMoviesListFragment : ListsBaseFragment() {

    private fun setSearchedMovies(page: Int, query: String) {
        movieListViewModel.getMovies(Constant.API_KEY, query, page.toString())
        movieListViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.code() == 200) {
                if (page == 1) ResultsLists.searchedMovies = response.body()?.movies!!
                else ResultsLists.searchedMovies.toMutableList().addAll(response.body()?.movies!!)
                ResultsLists.recyclerList = ResultsLists.searchedMovies
                setMoviesInRecycler()
            } else println(response.errorBody().toString())
        })
    }

    override fun setMovies() {
        ResultsLists.searchedMoviesPageNumber++
        setSearchedMovies(ResultsLists.searchedMoviesPageNumber, "flash")
    }


}