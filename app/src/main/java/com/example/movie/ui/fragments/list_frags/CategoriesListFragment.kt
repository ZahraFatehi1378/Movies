package com.example.movie.ui.fragments.list_frags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.data.api.util.Constant
import com.example.movie.data.api.viewmodels.GenresViewModel
import com.example.movie.data.api.viewmodels.factories.GenresViewModelFactory
import com.example.movie.ui.adaptor.HorizontalGenresAdaptor
import com.example.movie.ui.interfaces.OnHorizontalRecyclerListener
import kotlinx.coroutines.launch

class CategoriesListFragment : BaseFragment() {

    private lateinit var genresViewModel: GenresViewModel
    private var horizontalRecyclerView: RecyclerView? = null
    private var genresList = mutableListOf<GenreModel>()
    private lateinit var horizontalGenresAdaptor: HorizontalGenresAdaptor


    override fun setRoot(inflater: LayoutInflater, container: ViewGroup?) {
        rootView = inflater.inflate(R.layout.fragment_categories, container, false)
        noInternetAnim = rootView.findViewById(R.id.animation)

        horizontalGenresAdaptor = HorizontalGenresAdaptor(object : OnHorizontalRecyclerListener {
            override fun onItemClicked(position: Int, genre_id: Int) {


                movieListViewModel.setMoviesByGenre(genre_id)
                movieListViewModel.getMovies()
                movieListViewModel.myResponse.observe(viewLifecycleOwner, {
                    lifecycleScope.launch {
                        moviesAdaptor?.submitData(it)
                    }
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
        movieListViewModel.setMoviesByGenre(28)
        movieListViewModel.getMovies()
        movieListViewModel.myResponse.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                moviesAdaptor?.submitData(it)
            }
        })
    }


    private fun setCategorizedMovies() {
        val genresViewModelFactory = GenresViewModelFactory()
        genresViewModel =
            ViewModelProvider(this, genresViewModelFactory).get(GenresViewModel::class.java)
        genresViewModel.getGenres(Constant.API_KEY)
        genresViewModel.myResponse.observe(viewLifecycleOwner, { response ->
                genresList.addAll(response)
            if (!genresList.isEmpty())
                genresList[0].selected = true
                horizontalGenresAdaptor.notifyDataSetChanged()
        })
    }

}