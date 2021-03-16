package com.example.movie.ui.fragments.list_frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.request.Repository
import com.example.movie.request.viewmodels.MovieListViewModel
import com.example.movie.request.viewmodels.ViewModelFactory
import com.example.movie.response.ResultsLists
import com.example.movie.ui.adaptor.MoviesAdaptor
import com.example.movie.ui.interfaces.OnMovieListener


open class ListsBaseFragment : Fragment() {

    protected lateinit var movieListViewModel: MovieListViewModel
    protected var recyclerView: RecyclerView? = null
    protected var moviesAdaptor: MoviesAdaptor? = null
    protected lateinit var rootView :View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_base, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        init()
        return rootView
    }

    private fun init() {
        val viewModelFactory = ViewModelFactory(Repository)
        movieListViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        addRecyclerView()
        setMovies()
    }

    private fun addRecyclerView() {
        //        moviesAdaptor = MoviesAdaptor(ResultsLists.popularMovies)
//        recyclerView?.adapter  =  moviesAdaptor
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1))//movies in this page in over
                    setMovies()
            }
        })

    }

    fun getSelectedMovie(position: Int ){
        if (ResultsLists.recyclerList.isNotEmpty()){
            val action =
                PopularMoviesFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(
                    ResultsLists.recyclerList[position]
                )
            Navigation.findNavController(rootView).navigate(action)
        }

    }

    fun setMoviesInRecycler() {
        //todo ask why
        //  moviesAdaptor?.notifyDataSetChanged()

        moviesAdaptor = MoviesAdaptor(object :OnMovieListener{
            override fun onItemClicked(position: Int) {
                getSelectedMovie(position)
            }

        })
        recyclerView?.adapter = moviesAdaptor
    }


    open fun setMovies() {}


}