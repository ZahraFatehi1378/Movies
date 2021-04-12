package com.example.movie.data.api.viewmodels.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.request.repositories.MovieListRepository
import retrofit2.HttpException
import java.io.IOException

class PopularMoviePagingSource(var key: String, var repository: MovieListRepository) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val page = params.key ?: 1
        return try {
            val response: List<MovieModel> = repository.getPopularMovies(key, page.toString())!!
           // isMoviesArrayEmpty.postValue(response.isEmpty())
            LoadResult.Page(
                response, prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition
    }
}