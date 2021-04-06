package com.example.movie

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.movie.data.database.DataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context2 = this
        DataBase.getDatabase(this)
    }

    companion object {
        lateinit var context2: Context

        fun isNetworkAvailable(): Boolean {
            val cm = context2.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo?
            activeNetworkInfo = cm.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }

}