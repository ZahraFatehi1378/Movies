package com.example.movie.ui.fragments.list_frags

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.MainActivity
import com.example.movie.OnAboutDataReceivedListener
import com.example.movie.R
import com.example.movie.request.Repository
import com.example.movie.request.model.MovieModel
import com.example.movie.request.util.Constant
import com.example.movie.request.viewmodels.MovieListViewModel
import com.example.movie.request.viewmodels.MovieListViewModelFactory
import com.example.movie.ui.MovieDetailActivity
import com.example.movie.ui.adaptor.MoviesAdaptor
import com.example.movie.ui.interfaces.OnMovieListener


open class BaseFragment : Fragment() {

    protected lateinit var movieListViewModel: MovieListViewModel
    protected var recyclerView: RecyclerView? = null
    protected var moviesAdaptor: MoviesAdaptor? = null
    protected lateinit var rootView: View
    protected var recyclerList = mutableListOf<MovieModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_base, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        init()

        val mActivity = activity as MainActivity?
        mActivity?.setAboutDataListener(object : OnAboutDataReceivedListener {
            override fun onDataReceived(search: String) {
                movieListViewModel.getSearchedMovies(
                    Constant.API_KEY,
                    search,
                    "1"
                )
                movieListViewModel.myResponse.observe(viewLifecycleOwner, { response ->
                    if (response.code() == 200) {
                        println("yes")

                        recyclerList.clear()
                        // if (ResultsLists.searchedMoviesPageNumber.toString() == "1") {
                        recyclerList.addAll(response.body()?.movies!!)
                        moviesAdaptor?.notifyDataSetChanged()
//                        } else {
//                            recyclerList.addAll(response.body()?.movies!!)
//                        }
                    } else println(response.errorBody().toString())
                })

            }

        })
        return rootView
    }

    private fun init() {
        val viewModelFactory = MovieListViewModelFactory(Repository)
        movieListViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        addRecyclerView()
        setMovies()

    }

    private fun addRecyclerView() {
        moviesAdaptor = MoviesAdaptor(object : OnMovieListener {
            override fun onItemClicked(position: Int) {
                getSelectedMovie(position)
            }

        }, recyclerList)

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
//            val action =
//                PopularMoviesFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(
//                    recyclerList[position].id
//                )
//            Navigation.findNavController(rootView).navigate(action)
        //       }

    }
}