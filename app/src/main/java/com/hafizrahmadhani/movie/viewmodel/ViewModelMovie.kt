package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.core.domain.usecase.MovieUseCase

class ViewModelMovie (private val movieUseCase: MovieUseCase) : ViewModel() {

    fun callMovie() = movieUseCase.callMovieList()

    fun callTvShow() = movieUseCase.callTvShowList()
}


