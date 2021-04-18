package com.example.movie.ui.fragments.list_frags

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.movie.MainActivity
import com.example.movie.R
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.viewmodels.MovieListViewModel
import com.example.movie.data.api.viewmodels.factories.MovieListViewModelFactory
import com.example.movie.ui.MovieDetailActivity
import com.example.movie.ui.adaptor.MoviesAdaptor
import com.example.movie.ui.interfaces.OnAboutDataReceivedListener
import com.example.movie.ui.interfaces.OnBackPressedListener
import com.example.movie.ui.interfaces.OnRecyclerItemListener
import kotlinx.coroutines.launch


open class BaseFragment : Fragment() {

    protected lateinit var movieListViewModel: MovieListViewModel
    protected var recyclerView: RecyclerView? = null
    protected var moviesAdaptor: MoviesAdaptor? = null
    protected lateinit var rootView: View
    protected lateinit var noInternetAnim: LottieAnimationView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setRoot(inflater, container)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        init()
        return rootView
    }

    private fun setSearch() {
        (activity as MainActivity).searchImageView.visibility = View.VISIBLE
        (activity as MainActivity).searchImageView.isEnabled = true
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

        mActivity?.setBackPressesListener(object :OnBackPressedListener{
            override fun backPressed() {
             setMovies()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).searchImageView.visibility = View.VISIBLE
        (activity as MainActivity).searchImageView.isEnabled = true
        setMovies()
    }

    protected open fun setRoot(inflater: LayoutInflater, container: ViewGroup?) {
        rootView = inflater.inflate(R.layout.fragment_base, container, false)
        noInternetAnim = rootView.findViewById(R.id.animation)

    }

    private fun init() {
        val viewModelFactory = MovieListViewModelFactory()
        movieListViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        addRecyclerView()
        setMovies()
        setSearch()

    }

    protected open fun addRecyclerView() {
        moviesAdaptor = MoviesAdaptor(object : OnRecyclerItemListener {
            override fun onItemClicked(item: MovieModel?) {
                val intent = Intent(activity, MovieDetailActivity::class.java).apply {
                    putExtra("movie_id", item?.id)
                }
                startActivity(intent)
            }

        })

        moviesAdaptor?.addLoadStateListener {
            val isEmptyList = it.refresh is LoadState.NotLoading && moviesAdaptor!!.itemCount == 0
            setUi(isEmptyList)
        }
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

     protected open fun setUi(emptyList: Boolean) {
        if (emptyList) {
            noInternetAnim.visibility = View.VISIBLE
        } else {
            noInternetAnim.visibility = View.GONE
        }
    }


    open fun setMovies() {}

}