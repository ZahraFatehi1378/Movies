package com.example.movie.data.api.model.credits

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crews")
class CrewModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "job")
    val job: String,
    @ColumnInfo(name = "movie_id")
    var movie_id: Int

)