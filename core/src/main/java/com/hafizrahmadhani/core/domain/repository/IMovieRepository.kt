package com.hafizrahmadhani.core.domain.repository

import com.hafizrahmadhani.core.data.Resource
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovie
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovieDetail
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun callMovieList() : Flow<Resource<List<DataModelMovie>>>

    fun callDetailMovie(idMovie : Int) : Flow<Resource<DataModelMovieDetail>>

    fun callTvShowList() : Flow<Resource<List<DataModelMovie>>>

    fun callDetailTv(idTv : Int) : Flow<Resource<DataModelMovieDetail>>

    suspend fun insertMovieFavorite(movie : Int)

    suspend fun deleteMovieFavorite(movie : Int)

    fun callMovieFavorite() : Flow<List<DataModelMovie>>

    fun callTvShowFavorite() : Flow<List<DataModelMovie>>

    fun ifFavoriteMovie(id : Int) : Flow<Boolean>
}