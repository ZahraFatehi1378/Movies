package com.example.movie.data.database

import androidx.room.*
import com.example.movie.data.api.model.credits.CastModel
import com.example.movie.data.api.model.credits.CrewModel
import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.model.moviedetail.MovieDetailModel

@Dao
interface DataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail: MovieDetailModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genreModel: GenreModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrew(genreModel: CrewModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(genreModel: CastModel)

    @Query("select * from movie")
    suspend fun getAllMovies():List< MovieModel>

    @Query("select * from movie_detail where id= :id")
    suspend fun getMovieDetail(id : Int): MovieDetailModel

    @Query("select* from genre")
    suspend fun getGenres():MutableList<GenreModel>

    @Query("select * from crews where movie_id =:movie_id")
    suspend fun getCrews(movie_id:Int): List<CrewModel>

    @Query("select * from casts where movie_id =:movie_id")
    suspend fun getCasts(movie_id:Int): List<CastModel>

    @Query("select * from movie natural join movie_detail where isSaved = 1")
    suspend fun getSavedMovies():List< MovieModel>

}