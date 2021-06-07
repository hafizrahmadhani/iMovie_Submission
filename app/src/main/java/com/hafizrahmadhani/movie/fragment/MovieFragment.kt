package com.hafizrahmadhani.movie.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizrahmadhani.core.data.Resource
import com.hafizrahmadhani.movie.activity.MovieDetailActivity
import com.hafizrahmadhani.core.utils.TypeMovie
import com.hafizrahmadhani.movie.adapter.MovieAdapter
import com.hafizrahmadhani.movie.databinding.FragmentMovieBinding
import com.hafizrahmadhani.movie.viewmodel.ViewModelMovie
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModelMovie: ViewModelMovie by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extraType = arguments?.getInt(extraType)

        if (activity != null) {
            val listMovie = MovieAdapter {
                val detailintent = Intent(context, MovieDetailActivity::class.java)
                detailintent.putExtra(MovieDetailActivity.extraMovie, it.id)
                detailintent.putExtra(MovieDetailActivity.extraType, extraType)
                startActivity(detailintent)
            }

            binding.rvMovie.layoutManager = LinearLayoutManager(context)
            binding.rvMovie.setHasFixedSize(true)
            binding.rvMovie.adapter = listMovie

            val list = viewModelMovie.let {
                if (extraType == TypeMovie.typeMovie) {
                    it.callMovie()
                } else {
                    it.callTvShow()
                }
            }

            list.observe(viewLifecycleOwner, {
                when (it) {
                    is Resource.Loading -> callProgressBar(true)
                    is Resource.Success -> {
                        listMovie.setMovie(it.data!!)
                        callProgressBar(false)
                    }
                    is Resource.Error -> {
                        callProgressBar(false)
                        callNotifMessage(it.message as String)
                    }
                }
            })
        }
    }

    private fun callProgressBar(status: Boolean) {
        if (status) {
            binding.movProgressbar.visibility = View.VISIBLE
        } else {
            binding.movProgressbar.visibility = View.GONE
        }
    }

    private fun callNotifMessage(message: String) {
        binding.movProgressbar.visibility = View.GONE
        binding.notifMessageMov.apply {
            visibility = View.VISIBLE
            text = message
        }
    }

    companion object {
        const val extraType = "extra_type"
    }
}

