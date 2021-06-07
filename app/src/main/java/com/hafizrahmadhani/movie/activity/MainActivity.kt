package com.hafizrahmadhani.movie.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import com.hafizrahmadhani.movie.R
import com.hafizrahmadhani.movie.adapter.PagerAdapter
import com.hafizrahmadhani.movie.databinding.ActivityMainBinding
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            setSupportActionBar(toolbarTitle)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            pagerAdapter = PagerAdapter(this@MainActivity)
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tab, viewPager){
                tab, position -> tab.text = TAB_TITLES[position]
            }.attach()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite_menu -> {
                startActivity(Intent(this, Class.forName("com.hafizrahmadhani.favorite.activity.FavoriteActivity")))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        val TAB_TITLES = arrayOf("Movies", "TV Shows")
    }
}