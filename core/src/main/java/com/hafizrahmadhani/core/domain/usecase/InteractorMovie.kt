package com.hafizrahmadhani.core.domain.usecase

import androidx.lifecycle.asLiveData
import com.hafizrahmadhani.core.domain.repository.IMovieRepository

class InteractorMovie(private val iMovieRepository: IMovieRepository) : MovieUseCase {

    override fun callMovieList() = iMovieRepository.callMovieList().asLiveData()

    override fun callDetailMovie(idMovie: Int) = iMovieRepository.callDetailMovie(idMovie).asLiveData()

    override fun callTvShowList() = iMovieRepository.callTvShowList().asLiveData()

    override fun callDetailTv(idTv: Int) = iMovieRepository.callDetailTv(idTv).asLiveData()

    override suspend fun insertMovieFavorite(movie: Int) = iMovieRepository.insertMovieFavorite(movie)

    override suspend fun deleteMovieFavorite(movie: Int) = iMovieRepository.deleteMovieFavorite(movie)

    override fun callMovieFavorite() = iMovieRepository.callMovieFavorite().asLiveData()

    override fun callTvShowFavorite() = iMovieRepository.callTvShowFavorite().asLiveData()

    override fun ifFavoriteMovie(id: Int) = iMovieRepository.ifFavoriteMovie(id).asLiveData()


}