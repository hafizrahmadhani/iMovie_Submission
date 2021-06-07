package com.hafizrahmadhani.favorite.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.hafizrahmadhani.favorite.adapter.FavoritePagerAdapter
import com.hafizrahmadhani.favorite.databinding.ActivityFavoriteBinding
import com.hafizrahmadhani.favorite.di.favoriteModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.core.context.loadKoinModules

@InternalCoroutinesApi
class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteBinding : ActivityFavoriteBinding
    private lateinit var favoritePagerAdapter: FavoritePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)

        loadKoinModules(favoriteModule)
        with(favoriteBinding){
            favoritePagerAdapter = FavoritePagerAdapter(this@FavoriteActivity)
            viewPagerFavorite.adapter = favoritePagerAdapter
            TabLayoutMediator(tabFavorite, viewPagerFavorite)
            {
                    tabFavorite, position -> tabFavorite.text = TAB_TITLES[position]
            }.attach()
        }
    }

    companion object {
        val TAB_TITLES = arrayOf("Movies", "TV Shows")
    }
}