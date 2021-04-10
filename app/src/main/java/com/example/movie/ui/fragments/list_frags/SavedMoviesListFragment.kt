package com.example.movie.ui.fragments.list_frags

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.ui.MovieDetailActivity
import com.example.movie.ui.adaptor.SavedMoviesRecyclerAdaptor
import com.example.movie.ui.interfaces.OnRecyclerItemListener
import kotlinx.coroutines.launch

class SavedMoviesListFragment : BaseFragment() {


    private lateinit var savedMoviesAdaptor:SavedMoviesRecyclerAdaptor
    private lateinit var savedList: MutableList<MovieModel>
    override fun addRecyclerView(){
        savedList = mutableListOf()
        savedMoviesAdaptor = SavedMoviesRecyclerAdaptor(savedList, object : OnRecyclerItemListener {
            override fun onItemClicked(item: MovieModel?) {
                val intent = Intent(activity, MovieDetailActivity::class.java).apply {
                    putExtra("movie_id", item?.id)
                }
                startActivity(intent)
            }

        })
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = savedMoviesAdaptor
        setMovies()
    }
     override fun setSearch(){
         //todo hide search
     }

    override  fun setRoot(inflater: LayoutInflater, container: ViewGroup?) {
        rootView = inflater.inflate(R.layout.fragment_saved_movies_list, container, false)
    }

    override fun onResume() {
        super.onResume()
             setMovies()
    }

    override fun setMovies() {
        movieListViewModel.getSavedMovies()

        movieListViewModel.mySavedMoviesResponse.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                savedList = it as MutableList<MovieModel>
                Log.e("${savedList.size}" , ")))))))))))))))")
                savedMoviesAdaptor.onRefresh(savedList)
            }
        })
    }


}