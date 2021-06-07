package com.hafizrahmadhani.favorite.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hafizrahmadhani.core.utils.TypeMovie
import com.hafizrahmadhani.favorite.fragment.FavoriteMovieFragment
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoritePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FavoriteMovieFragment()
        val typeFragment =  when(position){
            0 -> TypeMovie.typeMovie
            1 -> TypeMovie.typeTvShow
            else -> TypeMovie.typeMovie
        }
        fragment.arguments = Bundle().apply { putInt(FavoriteMovieFragment.extraType, typeFragment) }
        return fragment
    }

}