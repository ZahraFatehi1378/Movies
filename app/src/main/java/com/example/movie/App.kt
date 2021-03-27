package com.example.movie

import android.app.Application
import com.example.movie.data.database.DataBase

class App :Application(){
    override fun onCreate() {
        super.onCreate()
        DataBase.getDatabase(this)
    }
}