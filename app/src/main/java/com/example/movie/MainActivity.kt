package com.example.movie

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.request.viewmodels.MovieDetailViewModel
import com.example.movie.ui.adaptor.PagerAdaptor
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var movieDetailModel: MovieDetailViewModel

    lateinit var viewPager: ViewPager2
    lateinit var searchText: EditText
    lateinit var imageView: ImageView
    lateinit var appBarLayout: AppBarLayout
    lateinit var searchListener : OnAboutDataReceivedListener


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        imageView = findViewById(R.id.search)
        searchText = findViewById(R.id.search_txt)
        searchText.visibility = View.INVISIBLE
      //  textView.isEnabled = false
        appBarLayout = findViewById(R.id.appBarLayout2)

        imageView.setOnClickListener{
            searchText.visibility = View.VISIBLE
            searchText.isEnabled = true
        //    textView.setBackgroundColor(R.color.text_color)

            appBarLayout.visibility = View.INVISIBLE
            appBarLayout.isEnabled = false
        }

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = PagerAdaptor(supportFragmentManager, lifecycle)


        searchText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                searchListener.onDataReceived(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })



        TabLayoutMediator(tabs, viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "POPULAR"
                }
                1 -> {
                    tab.text = "SAVED"
                }
                2 -> {
                    tab.text = "CATEGORIES"
                }

            }
        }.attach()

    }

    fun setAboutDataListener(listener:OnAboutDataReceivedListener ) {
        this.searchListener = listener;
    }
}

 interface OnAboutDataReceivedListener {
    fun onDataReceived(search : String);
}