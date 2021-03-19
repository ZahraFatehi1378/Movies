package com.example.movie.ui.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.MovieItemBinding
import com.example.movie.request.model.movie.MovieModel
import com.example.movie.ui.interfaces.OnRecyclerItemListener


class MoviesAdaptor(
    private val listener: OnRecyclerItemListener,
    private var list: MutableList<MovieModel>
) : RecyclerView.Adapter<MoviesAdaptor.MovieViewHandler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHandler {
        val binding: MovieItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_item, parent, false
        )

        return MovieViewHandler(binding)

    }

    override fun onBindViewHolder(holder: MovieViewHandler, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieViewHandler(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ), View.OnClickListener {

        fun bindItems(movie: MovieModel) {
            itemView.setOnClickListener(this)
            binding.movieModel = movie
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            listener.onItemClicked(adapterPosition)
        }

    }


}


