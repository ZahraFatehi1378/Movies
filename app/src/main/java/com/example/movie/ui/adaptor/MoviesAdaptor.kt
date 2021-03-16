package com.example.movie.ui.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.request.model.MovieModel
import com.example.movie.response.ResultsLists
import com.example.movie.ui.interfaces.OnMovieListener
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdaptor( private val listener: OnMovieListener) : RecyclerView.Adapter<MoviesAdaptor.MovieViewHandler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHandler {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.movie_item, parent, false)
        return MovieViewHandler(view)
    }

    override fun onBindViewHolder(holder: MovieViewHandler, position: Int) {
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500"+ResultsLists.recyclerList[position].poster_path).into(holder.itemView.imageView)
        holder.bindItems(ResultsLists.recyclerList[position])
    }

    override fun getItemCount(): Int {
        return ResultsLists.recyclerList.size
    }

  inner  class MovieViewHandler(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        fun bindItems(movie :MovieModel) {
            itemView.title.text = movie.title
          //  itemView.time.text
            //  itemView.time.text
            itemView.rate.text = movie.vote_average.toString()
            itemView.ratingBar.rating = movie.vote_average/2

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClicked(adapterPosition , )
        }

    }


}


