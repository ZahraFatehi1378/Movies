package com.example.movie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.movie.R
import com.example.movie.databinding.FragmentMovieDetailsBinding
import com.example.movie.request.model.MovieModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val args:MovieDetailsFragmentArgs by navArgs()

    private var binding: FragmentMovieDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        println(args.chosenMovie.title+"oh")
        val binding = FragmentMovieDetailsBinding.inflate(inflater , container , false)
        binding.chosenMovie = args.chosenMovie
        return binding.root
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        binding = null
        super.onDestroyView()
    }
}