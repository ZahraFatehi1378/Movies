package com.example.movie.data.api.viewmodels.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.request.repositories.MovieListRepository
import retrofit2.HttpException
import java.io.IOException

class PopularMoviePagingSource(var key: String, var repository: MovieListRepository) :
    PagingSource<Int, MovieModel>() {

    //  lateinit var response: Response<MovieModel>
    var dbPage = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val page = params.key ?: 1
        return try {
            val response: List<MovieModel> = repository.getPopularMovies(key, page.toString())!!

            //           val repositoryResponse = repository.getPopularMovies(key, page.toString())
            Log.e("333333333", "33333333333333333")
//            if (repositoryResponse.code() == 200) {
            //               val response = repositoryResponse.body()!!.movies
//                DataBase.getDatabase().withTransaction {
//                    DataBase.getDatabase().dataBaseDao().insertMovie(response)
//                }
            LoadResult.Page(
                response, prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
//            } else {
//                val res =
//                    DataBase.getDatabase().dataBaseDao().getLimitMovies(dbPage, dbPage.plus(20))
//                LoadResult.Page(
//                    res, prevKey = null,
//                    nextKey = page + 1
//                )
//            }


        } catch (exception: IOException) {
//            GlobalScope.launch(Dispatchers.IO) {
//                val res2 = DataBase.getDatabase().dataBaseDao().getLimitMovies(dbPage, dbPage.plus(20))
//                 LoadResult.Page(
//                    res2, prevKey = null,
//                    nextKey = page + 1
//                )
//            }
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition
    }
}