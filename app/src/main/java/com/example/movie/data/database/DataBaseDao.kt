package com.example.movie.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.model.moviedetail.MovieDetailModel

@Dao
interface DataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieModel>)

    @Query("SELECT * FROM movie WHERE id = :id")
    fun movieById(id : Int): PagingSource<Int, MovieModel>

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteMovieById(id:Int)

    @Insert
    suspend fun insertMovieDetail(movieDetail : MovieDetailModel)
//
//    @Insert
//    suspend fun insertGenre(genreModel: GenreModel)



//    @Delete
//    suspend fun deleteMovieDetail(movieDetail : MovieDetailTable)
//
//    @Delete
//    suspend fun deleteGenre(genreModel: GenreModel)

    @Query("select * from movie where id= :id")
    fun getAllMovies(id : Int):List<MovieModel>
//
//    @Query("select * from movie_detail where id= :id")
//    fun getMovieDetail(id : Int):MovieDetailTable
//
//    @Query("select* from genre")
//    fun getGenres():List<GenreModel>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun pagingSource(id : Int): PagingSource<Int, MovieModel>

}