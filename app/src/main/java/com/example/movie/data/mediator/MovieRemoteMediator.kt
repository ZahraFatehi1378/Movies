package com.example.movie.data.mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.util.Constant
import com.example.movie.data.api.util.MovieApi
import com.example.movie.data.database.DataBase


@OptIn(ExperimentalPagingApi::class)
open class MovieRemoteMediator(
    private val database: DataBase,
    private val movieApiService: MovieApi
) : RemoteMediator<Int, MovieModel>() {
    val dataBaseDao = database.dataBaseDao()
    var key = 1

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieModel>): MediatorResult {

//        val page = when (loadType) {
//            LoadType.REFRESH -> 1
//            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//            LoadType.APPEND -> {
//                val nextKey = state.pages.lastOrNull()?.nextKey
//                nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)
//            }
//        }

        return try {

            val response = movieApiService.getPopularMovies(Constant.API_KEY, page = key.toString())
            database.withTransaction {
                if (response.body() != null) {
                    dataBaseDao.insertMovie(response.body()!!.movies)
                    key++
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.body()!!.movies.isEmpty())
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }
}
