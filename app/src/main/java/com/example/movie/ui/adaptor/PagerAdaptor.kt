package com.example.movie.ui.adaptor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.ui.fragments.list_frags.CategoriesListFragment
import com.example.movie.ui.fragments.list_frags.PopularMoviesFragment
import com.example.movie.ui.fragments.list_frags.SavedMoviesListFragment



class PagerAdaptor(supportFragmentManager:FragmentManager, lifecycle:Lifecycle) : FragmentStateAdapter(supportFragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PopularMoviesFragment()
            1 -> return SavedMoviesListFragment()
            2 -> return CategoriesListFragment()
        }
        return PopularMoviesFragment()
    }
}