package com.hafizrahmadhani.movie.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hafizrahmadhani.core.utils.TypeMovie
import com.hafizrahmadhani.movie.fragment.MovieFragment
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class PagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = MovieFragment()
        val typeFragment = when(position){
            0 -> TypeMovie.typeMovie
            1 -> TypeMovie.typeTvShow
            else -> TypeMovie.typeMovie
        }
        fragment.arguments = Bundle().apply { putInt(MovieFragment.extraType, typeFragment) }
        return fragment
    }
}