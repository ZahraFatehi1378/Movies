package com.example.movie.request.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.request.Repository
import com.example.movie.request.viewmodels.GenresViewModel

class GenresViewModelFactory (private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GenresViewModel(repository) as T
    }
}
