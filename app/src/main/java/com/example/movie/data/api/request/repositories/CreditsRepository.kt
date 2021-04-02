package com.example.movie.data.api.request.repositories

import com.example.movie.data.api.model.credits.CastModel
import com.example.movie.data.api.model.credits.CreditResponse
import com.example.movie.data.api.model.credits.CrewModel
import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.data.api.model.genre.GenreResponse
import com.example.movie.data.api.request.RetrofitInstance
import com.example.movie.data.database.DataBase
import retrofit2.Response

class CreditsRepository {
    private val dbDao = DataBase.getDatabase().dataBaseDao()
    private val api = RetrofitInstance.api
    lateinit var result: CreditResponse
    private lateinit var credits: Response<CreditResponse>

    suspend fun getCredits(key: String , movie_id:Int): CreditResponse {
        result = CreditResponse(dbDao.getCasts(movie_id) , dbDao.getCrews(movie_id))
        if (result.cast.isEmpty() || result.crew.isEmpty()) {
            credits = api.getCredits(movie_id, key)
            if (credits.code() == 200) {
                val casts = credits.body()!!.cast
                val crews = credits.body()!!.crew
                result = CreditResponse(casts, crews)
                for (crew : CrewModel in crews){
                    dbDao.insertCrew(crew)
                }
                for (cast : CastModel in casts){
                    dbDao.insertCast(cast)
                }
            } else {
                println(credits.errorBody().toString())
            }
        }
        return result
    }
}