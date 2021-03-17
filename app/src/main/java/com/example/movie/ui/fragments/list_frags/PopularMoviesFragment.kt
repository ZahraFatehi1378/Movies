package com.example.movie.ui.fragments.list_frags

import com.example.movie.request.util.Constant
import com.example.movie.response.ResultsLists


class PopularMoviesFragment : ListsBaseFragment() {


    override fun setMovies() {
        ResultsLists.popularPageNumber++
        setPopularMovies(ResultsLists.popularPageNumber.toString())
    }

    private fun setPopularMovies(page: String) {
        movieListViewModel.getPopularMovies(Constant.API_KEY, page)
        movieListViewModel.myResponse.observe(viewLifecycleOwner, { response ->
            if (response.code() == 200) {
                if (page == "1") {
                    recyclerList.addAll(response.body()?.movies!!)
                    //     recyclerList = (response.body()?.movies as MutableList<MovieModel>?)!!
                    moviesAdaptor?.notifyDataSetChanged()
                } else {
                    recyclerList.addAll(response.body()?.movies!!)
                    // moviesAdaptor?.notifyItemRangeChanged( recyclerList.size -21, 20)
                }
            } else println(response.errorBody().toString())
        })
    }


}