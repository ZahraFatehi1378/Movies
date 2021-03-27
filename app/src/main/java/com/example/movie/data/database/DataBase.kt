package com.example.movie.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movie.data.api.model.credits.CastModel
import com.example.movie.data.api.model.credits.CrewModel
import com.example.movie.data.api.model.genre.GenreModel
import com.example.movie.data.api.model.movie.MovieModel
import com.example.movie.data.api.model.moviedetail.MovieDetailModel

@Database(
    entities = arrayOf(
        MovieModel::class,
        MovieDetailModel::class,
        GenreModel::class,
        CastModel::class,
        CrewModel::class
    ), version = 1, exportSchema = false
)
@TypeConverters(Converters::class)

abstract class DataBase : RoomDatabase() {

    abstract fun dataBaseDao(): DataBaseDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        fun getDatabase():DataBase{
            return INSTANCE!!
        }
    }
}


