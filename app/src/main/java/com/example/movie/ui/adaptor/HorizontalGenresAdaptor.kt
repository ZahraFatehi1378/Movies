package com.example.movie.ui.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.CategoryItemBinding
import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.ui.interfaces.OnHorizontalRecyclerListener

class HorizontalGenresAdaptor(
    private val listener: OnHorizontalRecyclerListener,
    private var list: MutableList<GenreModel>
) : RecyclerView.Adapter<HorizontalGenresAdaptor.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding: CategoryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_item, parent, false
        )

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ), View.OnClickListener {
        lateinit var genre: GenreModel

        fun bindItems(genre: GenreModel) {
            itemView.setOnClickListener(this)
            this.genre = genre
            binding.genreModel = genre
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            for (x in list) {
                x.selected = false
            }
            listener.onItemClicked(adapterPosition, genre.id)
            genre.selected = true

            notifyDataSetChanged()
        }

    }
}