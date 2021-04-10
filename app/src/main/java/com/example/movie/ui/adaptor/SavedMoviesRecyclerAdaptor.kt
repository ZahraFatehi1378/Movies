package com.example.movie.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.databinding.MovieItemBinding
import com.example.movie.ui.interfaces.OnRecyclerItemListener


class SavedMoviesRecyclerAdaptor(private var moviesList: List<MovieModel>, private val listener: OnRecyclerItemListener) :
    RecyclerView.Adapter<SavedMoviesRecyclerAdaptor.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_item, parent, false
        )

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItems(moviesList[position]!!)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(moviesList[position])
        }
    }


    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun onRefresh(moviesList: List<MovieModel>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(movie: MovieModel) {
            binding.movieModel = movie
            binding.executePendingBindings()
        }
    }

}