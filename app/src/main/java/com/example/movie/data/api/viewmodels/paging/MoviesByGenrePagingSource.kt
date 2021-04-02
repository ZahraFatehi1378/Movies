package com.example.movie.data.api.viewmodels.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.request.repositories.MovieListRepository
import retrofit2.HttpException
import java.io.IOException

class MoviesByGenrePagingSource(
    var key: String,
    var genre_id:Int, var repository: MovieListRepository
) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 1
        return try {
            val response = repository.getMoviesOfTheGenre( key , genre_id).body()!!.movies
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

}