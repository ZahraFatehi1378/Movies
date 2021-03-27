package com.example.movie.data.api.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.data.api.viewmodels.GenresViewModel

class GenresViewModelFactory (): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GenresViewModel() as T
    }
}
