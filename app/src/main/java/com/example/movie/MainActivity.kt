package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.request.viewmodels.MovieDetailModel
import com.example.movie.ui.adaptor.PagerAdaptor
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var movieDetailModel: MovieDetailModel

    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewPager = findViewById(R.id.viewPager)

        viewPager.adapter = PagerAdaptor(supportFragmentManager, lifecycle)



        TabLayoutMediator(tabs, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.text = "POPULAR"}
                    1 -> { tab.text = "SAVED"}
                    2 -> {tab.text = "CATEGORIES"}

                }
            }).attach()

    }


//    private fun setMovieDetails(id: Int) {
//        movieDetailModel.getMovieAccordingToId(id, API_KEY)
//        movieDetailModel.myResponse.observe(this, Observer { response ->
//            if (response.code() == 200) {
//                print(response.body()?.movie)
//
//            } else {
//            }
//        })
//
//    }

}