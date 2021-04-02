package com.example.movie.data.api.request.repositories

import android.util.Log
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.model.movie.MovieSearchResponse
import com.example.movie.data.api.request.RetrofitInstance
import com.example.movie.data.database.DataBase
import retrofit2.Response

class MovieListRepository {

    private val dbDao = DataBase.getDatabase().dataBaseDao()
    private val api = RetrofitInstance.api
    var result: List<MovieModel>? = null
    private lateinit var moviesList: Response<MovieSearchResponse>

    suspend fun getPopularMovies(key: String, page: String): List<MovieModel>? {
 //       CoroutineScope(context = Dispatchers.IO).launch {
 //           val avar = async {
  //             val awar =async {
                   result = dbDao.getAllMovies()
                   Log.e("----11111111111", "$result")

//               }
//            awar.await()
                Log.e("00000000000", "$result")
   //         return@launch

            if (result == null) {
                moviesList = api.getPopularMovies(key, page)
                if (moviesList.code() == 200) {
                    result = moviesList.body()?.movies
                    dbDao.insertMovie(result!!)
                } else {
                    println(moviesList.errorBody().toString())
                    result = dbDao.getAllMovies()
                }
            }
//            }
//            avar.await()
  //      }
        Log.e("1111111111" , "$result")
        return result
    }

    suspend fun getSearchedMovies(
        key: String,
        query: String,
        page: String
    ): Response<MovieSearchResponse> {
        return RetrofitInstance.api.getSearchedMovies(key, query, page)
    }

    suspend fun getMoviesOfTheGenre(key: String, genre_id: Int): Response<MovieSearchResponse> {
        return RetrofitInstance.api.getMoviesOfTheGenre(key, genre_id)
    }

}