package com.hafizrahmadhani.movie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizrahmadhani.core.data.Resource
import com.hafizrahmadhani.movie.R
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovie
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovieDetail
import com.hafizrahmadhani.core.utils.TypeMovie
import com.hafizrahmadhani.movie.databinding.ActivityMovieDetailBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelDetailMovie
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModelDetailMovie: ViewModelDetailMovie by viewModel()
    private lateinit var dataModelMovieDetail: DataModelMovieDetail

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val typeMovie = intent.getIntExtra(extraType, TypeMovie.typeMovie)
        val idMovie = intent.getIntExtra(extraMovie, 0)

        var statusFavoriteMovie = false

        val detail = viewModelDetailMovie.let {
            if (typeMovie == TypeMovie.typeMovie) it.callDetailMovie(idMovie)
            else it.callDetailTvShow(idMovie)
        }

        detail.observe(this, {
            when (it) {
                is Resource.Loading -> callProgressBar(true)
                is Resource.Success -> {
                    callProgressBar(false)
                    dataModelMovieDetail = it.data as DataModelMovieDetail
                    callDetailData(dataModelMovieDetail)
                }
                is Resource.Error -> {
                    callProgressBar(false)
                    callNotifMessage(it.message as String)
                }
            }
        })

        viewModelDetailMovie.ifFavoriteMovie(idMovie).observe(this, {
            statusFavoriteMovie = it
            val status = if (it) {
                R.drawable.ic_bookmark_blue
            } else {
                R.drawable.ic_bookmark_blank
            }
            binding.fabDetail.setImageResource(status)
        })

        binding.fabDetail.setOnClickListener {
            val favorite = callDetailMovie(dataModelMovieDetail, typeMovie)
            if (statusFavoriteMovie) {
                viewModelDetailMovie.deleteMovieFavorite(favorite)
                Toast.makeText(this, "Dihapus dari Favorit", Toast.LENGTH_SHORT).show()
            } else {
                viewModelDetailMovie.insertMovieFavorite(favorite)
                Toast.makeText(this, "Disimpan ke dalam Favorit", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun callDetailMovie(detail: DataModelMovieDetail, type: Int): DataModelMovie {
        return DataModelMovie(
            detail.id,
            detail.title,
            detail.description,
            detail.image,
            detail.voteAverage,
            type
        )
    }

    private fun callDetailData(detail: DataModelMovieDetail) {
        detail.let { it ->
            Glide.with(this@MovieDetailActivity).load(posterSize + detail.image)
                .apply(RequestOptions().override(150, 200)).into(binding.movPoster2)
            Glide.with(this@MovieDetailActivity).load(imageSize + detail.poster)
                .apply(RequestOptions().override(0, 400)).into(binding.movPoster)
            binding.movTitle.text = it.title
            binding.movDate.text = it.date
            binding.movDescription.text = it.description
            binding.movGenre.text = it.genre.joinToString{it.name}
        }
    }

    private fun callProgressBar(status: Boolean) {
        if (status) {
            binding.progressbarDetail.visibility = View.VISIBLE
        } else {
            binding.progressbarDetail.visibility = View.GONE
        }
    }

    private fun callNotifMessage(message: String) {
        binding.fabDetail.visibility = View.GONE
        binding.progressbarDetail.visibility = View.GONE
        binding.notifMessage.apply {
            visibility = View.VISIBLE
            text = message
        }
    }

    companion object {
        const val extraType = "extra_type"
        const val extraMovie = "extra_movie"
        const val posterSize = "https://image.tmdb.org/t/p/w300/"
        const val imageSize = "https://image.tmdb.org/t/p/w500/"
    }
}
