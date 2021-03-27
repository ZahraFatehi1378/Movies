package com.example.movie.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.util.Constant
import com.example.movie.data.api.util.MovieApi
import com.example.movie.data.database.DataBase
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
open class MovieRemoteMediator<T, U>(
    private val movie_id: Int,
    private val database: DataBase,
    private val movieApiService: MovieApi
) : RemoteMediator<Int, MovieModel>() {
    val dataBaseDao = database.dataBaseDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieModel>
    ): RemoteMediator.MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    lastItem.id
                }
            }


            val response = movieApiService.getPopularMovies(Constant.API_KEY, loadKey.toString())
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dataBaseDao.deleteMovieById(id = movie_id)
                }
                dataBaseDao.insertMovie(response.body()!!.movies)
            }

            MediatorResult.Success(endOfPaginationReached = loadKey == response.body()?.total_pages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}
