package com.hafizrahmadhani.favorite.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizrahmadhani.core.utils.TypeMovie
import com.hafizrahmadhani.favorite.databinding.FragmentFavoriteMovieBinding
import com.hafizrahmadhani.favorite.viewmodel.ViewModelFavoriteMovie
import com.hafizrahmadhani.movie.activity.MovieDetailActivity
import com.hafizrahmadhani.movie.adapter.MovieAdapter
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel


@InternalCoroutinesApi
class FavoriteMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMovieBinding
    private val viewModelFavoriteMovie : ViewModelFavoriteMovie by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extraType = arguments?.getInt(extraType)

        if (activity != null) {
            val movieAdapter = MovieAdapter {
                val detailIntent = Intent(context, MovieDetailActivity::class.java)
                detailIntent.putExtra(MovieDetailActivity.extraMovie, it.id)
                detailIntent.putExtra(MovieDetailActivity.extraType, extraType)
                startActivity(detailIntent)
            }

            val favorite = viewModelFavoriteMovie.let {
                if(extraType == TypeMovie.typeMovie) {
                    it.callFavoriteMovie()
                } else{
                    it.callFavoriteTvShow()
                }
            }

            favorite.observe(viewLifecycleOwner, {
                movieAdapter.setMovie(it)
                callProgressBar(false)
            })

            viewModelFavoriteMovie.callNotifMessage().observe(viewLifecycleOwner, {
                if(it.isNotEmpty()){
                    binding.notifMessageMovFavorite.apply {
                        visibility = View.VISIBLE
                        text = it
                    }
                }else{
                    binding.notifMessageMovFavorite.visibility = View.GONE
                }
            })

            binding.rvMovieFavorite.layoutManager = LinearLayoutManager(context)
            binding.rvMovieFavorite.setHasFixedSize(true)
            binding.rvMovieFavorite.adapter = movieAdapter
        }
    }

    private fun callProgressBar(status: Boolean) {
        if (status) {
            binding.movProgressbarFavorite.visibility = View.VISIBLE
        } else {
            binding.movProgressbarFavorite.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.root.removeAllViews()
    }

    companion object {
        const val extraType = "extra_type"
    }
}