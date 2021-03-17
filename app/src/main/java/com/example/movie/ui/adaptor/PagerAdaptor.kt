package com.example.movie.ui.adaptor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.MainActivity
import com.example.movie.ui.fragments.list_frags.PopularMoviesFragment
import com.example.movie.ui.fragments.options.CategoriesFragment
import com.example.movie.ui.fragments.options.PopularFragment
import com.example.movie.ui.fragments.options.SavedFragment
import com.example.movie.ui.fragments.options.SearchedFragment


class PagerAdaptor(supportFragmentManager:FragmentManager, lifecycle:Lifecycle) : FragmentStateAdapter(supportFragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PopularFragment()
            1 -> return SavedFragment()
            2 -> return CategoriesFragment()
        }
        return PopularMoviesFragment()
    }
}