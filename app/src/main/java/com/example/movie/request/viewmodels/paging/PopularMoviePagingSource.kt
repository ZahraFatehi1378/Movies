package com.example.movie.request.viewmodels.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.request.Repository
import com.example.movie.request.model.movie.MovieModel
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class PopularMoviePagingSource(var key: String, var repository: Repository) : PagingSource<Int, MovieModel>() {

    lateinit var response: Response<MovieModel>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 1
        return try {
            val response = repository.getPopularMovies( key , page.toString()).body()!!.movies
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