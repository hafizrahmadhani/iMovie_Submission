package com.hafizrahmadhani.core.domain.usecase

import androidx.lifecycle.LiveData
import com.hafizrahmadhani.core.data.Resource
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovie
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovieDetail

interface MovieUseCase {
    fun callMovieList() : LiveData<Resource<List<DataModelMovie>>>

    fun callDetailMovie(idMovie : Int) : LiveData<Resource<DataModelMovieDetail>>

    fun callTvShowList() : LiveData<Resource<List<DataModelMovie>>>

    fun callDetailTv(idTv : Int) : LiveData<Resource<DataModelMovieDetail>>

    suspend fun insertMovieFavorite(movie : Int)

    suspend fun deleteMovieFavorite(movie : Int)

    fun callMovieFavorite() : LiveData<List<DataModelMovie>>

    fun callTvShowFavorite() : LiveData<List<DataModelMovie>>

    fun ifFavoriteMovie(id : Int) : LiveData<Boolean>
}