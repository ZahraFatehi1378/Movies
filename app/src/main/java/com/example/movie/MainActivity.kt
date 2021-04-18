package com.example.movie

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.ui.adaptor.PagerAdaptor
import com.example.movie.ui.interfaces.OnAboutDataReceivedListener
import com.example.movie.ui.interfaces.OnBackPressedListener
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var viewPager: ViewPager2
    lateinit var searchText: EditText
    lateinit var searchImageView: ImageView
    lateinit var appBarLayout: AppBarLayout
    lateinit var searchListener: OnAboutDataReceivedListener
    lateinit var onBackPressedListener: OnBackPressedListener

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        searchImageView = findViewById(R.id.search)
        searchText = findViewById(R.id.search_txt)
        searchText.visibility = View.INVISIBLE
        appBarLayout = findViewById(R.id.appBarLayout2)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = PagerAdaptor(supportFragmentManager, lifecycle)

        searchImageView.setOnClickListener {
            searchText.visibility = View.VISIBLE
            searchText.isEnabled = true
            searchText.setText("")
            searchText.requestFocus()
            val inputMethodManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(
                searchImageView.applicationWindowToken,
                InputMethodManager.SHOW_FORCED, 0
            )
            appBarLayout.visibility = View.INVISIBLE
            appBarLayout.isEnabled = false
        }

        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty())
                    searchListener.onDataReceived(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        TabLayoutMediator(
            tabs, viewPager
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

    override fun onResume() {
        super.onResume()
        searchImageView.visibility = View.VISIBLE
        searchImageView.isEnabled = true
        searchText.visibility = View.INVISIBLE
        searchText.isEnabled = false
        appBarLayout.visibility = View.VISIBLE
        appBarLayout.isEnabled = true

    }

    override fun onBackPressed() {
        if (searchText.visibility == View.INVISIBLE) finish()
        else {
            searchText.visibility = View.INVISIBLE
            searchText.isEnabled = false
            appBarLayout.visibility = View.VISIBLE
            appBarLayout.isEnabled = true
            onBackPressedListener.backPressed()
        }
    }

    fun setAboutDataListener(listener: OnAboutDataReceivedListener) {
        this.searchListener = listener;
    }

    fun setBackPressesListener(onBackPressedListener: OnBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener
    }

}

