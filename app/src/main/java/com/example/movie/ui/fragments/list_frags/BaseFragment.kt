package com.example.movie.ui.fragments.list_frags

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.MainActivity
import com.example.movie.R
import com.example.movie.request.Repository
import com.example.movie.request.model.movie.MovieModel
import com.example.movie.request.util.Constant
import com.example.movie.request.viewmodels.MovieListViewModel
import com.example.movie.request.viewmodels.factories.MovieListViewModelFactory
import com.example.movie.ui.MovieDetailActivity
import com.example.movie.ui.adaptor.MoviesAdaptor
import com.example.movie.ui.interfaces.OnAboutDataReceivedListener
import com.example.movie.ui.interfaces.OnRecyclerItemListener
import kotlinx.coroutines.launch


open class BaseFragment : Fragment() {

    protected lateinit var movieListViewModel: MovieListViewModel
    private var recyclerView: RecyclerView? = null
    protected var moviesAdaptor: MoviesAdaptor? = null
    protected lateinit var rootView: View
    protected var recyclerList = mutableListOf<MovieModel>()
    protected var page = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setRoot(inflater, container)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        init()
        setSearch()
        return rootView
    }

    private fun setSearch() {
        val mActivity = activity as MainActivity?
        mActivity?.setAboutDataListener(object : OnAboutDataReceivedListener {
            override fun onDataReceived(search: String) {
                movieListViewModel.setSearchedMovies(search)
                movieListViewModel.getMovies()
                movieListViewModel.myResponse.observe(viewLifecycleOwner, {
                    lifecycleScope.launch {
                        moviesAdaptor?.submitData(it)
                    }
                })

            }

        })
    }

    protected open fun setRoot(inflater: LayoutInflater, container: ViewGroup?) {
        rootView = inflater.inflate(R.layout.fragment_base, container, false)
    }

    private fun init() {
        val viewModelFactory = MovieListViewModelFactory(Repository)
        movieListViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        addRecyclerView()
        setMovies()

    }

    private fun addRecyclerView() {
        moviesAdaptor = MoviesAdaptor(object : OnRecyclerItemListener {
            override fun onItemClicked(position: Int) {
                getSelectedMovie(position)
            }

        })

        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = moviesAdaptor

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1))//movies in this page in over
                    setMovies()
            }
        })

    }


    open fun setMovies() {}


    fun getSelectedMovie(position: Int) {
        if (recyclerList.isNotEmpty()) {

            val intent = Intent(activity, MovieDetailActivity::class.java).apply {
                putExtra("movie_id", recyclerList[position].id)
            }
            startActivity(intent)
        }
    }
}