package com.example.movie.ui.fragments.list_frags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.request.Repository
import com.example.movie.request.model.genre.GenreModel
import com.example.movie.request.util.Constant
import com.example.movie.request.viewmodels.GenresViewModel
import com.example.movie.request.viewmodels.factories.GenresViewModelFactory
import com.example.movie.ui.adaptor.HorizontalGenresAdaptor
import com.example.movie.ui.interfaces.OnHorizontalRecyclerListener
import com.example.movie.ui.interfaces.OnRecyclerItemListener

class CategoriesListFragment : BaseFragment() {

    private lateinit var genresViewModel: GenresViewModel
    private var horizontalRecyclerView: RecyclerView? = null
    private var genresList = mutableListOf<GenreModel>()
    private lateinit var horizontalGenresAdaptor: HorizontalGenresAdaptor


    override fun setRoot(inflater: LayoutInflater, container: ViewGroup?) {
        rootView = inflater.inflate(R.layout.fragment_categories, container, false)

        horizontalGenresAdaptor = HorizontalGenresAdaptor(object : OnHorizontalRecyclerListener {
            override fun onItemClicked(position: Int, genre_id: Int) {
                println("$genre_id             00000000000000000000")
                movieListViewModel.getMoviesOfTheGenre(Constant.API_KEY, genre_id)
                movieListViewModel.myResponse.observe(viewLifecycleOwner, { response ->
                    if (response.code() == 200) {
                        recyclerList.clear()
                        // if (ResultsLists.searchedMoviesPageNumber.toString() == "1") {
                        recyclerList.addAll(response.body()?.movies!!)
                        moviesAdaptor?.notifyDataSetChanged()
                    } else println(response.errorBody().toString())
                })
            }
        }, genresList)
        horizontalRecyclerView = rootView.findViewById(R.id.horizontalRecyclerView)

        horizontalRecyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        horizontalRecyclerView?.adapter = horizontalGenresAdaptor
    }

    override fun setMovies() {
        setCategorizedMovies()
    }

    private fun setCategorizedMovies() {
        val genresViewModelFactory = GenresViewModelFactory(Repository)
        genresViewModel = ViewModelProvider(this, genresViewModelFactory).get(GenresViewModel::class.java)
        genresViewModel.getGenres(Constant.API_KEY)
        genresViewModel.myResponse.observe(viewLifecycleOwner, { response ->
            if (response.code() == 200) {
                genresList.addAll((response.body()?.genres as MutableList<GenreModel>?)!!)
                horizontalGenresAdaptor.notifyDataSetChanged()
            } else println(response.errorBody().toString())
        })
    }

}