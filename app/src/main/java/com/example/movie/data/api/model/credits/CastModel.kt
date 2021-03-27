package com.example.movie.data.api.model.credits

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "casts")
data class CastModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name:String ,
    @ColumnInfo(name = "character")
    val character:String,
    @ColumnInfo(name = "movie_id")
    val movie_id:Int
)