package com.example.movie.data.database

import androidx.room.TypeConverter
import com.example.movie.data.api.model.genre.GenreModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun fromCountryLangList(countryLang: List<GenreModel?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<GenreModel?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<GenreModel>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<GenreModel?>?>() {}.type
        return gson.fromJson<List<GenreModel>>(countryLangString, type)
    }
}
