package com.example.movie.data.api.request.repositories

import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.data.api.model.genre.GenreResponse
import com.example.movie.data.api.request.RetrofitInstance
import com.example.movie.data.database.DataBase
import retrofit2.Response

class GenresRepository {
    private val dbDao = DataBase.getDatabase().dataBaseDao()
    private val api = RetrofitInstance.api
    lateinit var result: MutableList<GenreModel>
    private lateinit var genres: Response<GenreResponse>

    suspend fun getGenres(key: String): List<GenreModel> {
        result = dbDao.getGenres()
        if (result.size == 0) {
            genres = api.getGenres(key)
            if (genres.code() == 200) {
                result = genres.body()?.genres as MutableList<GenreModel>
                for (genre:GenreModel in result)
                    dbDao.insertGenre(genre)
            } else {
                println(genres.errorBody().toString())
            }
        }
        return result
    }
}