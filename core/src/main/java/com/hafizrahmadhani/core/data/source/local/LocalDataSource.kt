package com.hafizrahmadhani.core.data.source.local

import com.hafizrahmadhani.core.data.source.local.entity.MovieEntity
import com.hafizrahmadhani.core.data.source.local.database.MovieDao
import com.hafizrahmadhani.core.data.source.local.entity.DetailMovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val mMovieDao: MovieDao) {

    fun callListMovie() : Flow<List<MovieEntity>> = mMovieDao.callListMovie()

    fun callDetailMovie(id : Int) : Flow<DetailMovieEntity> = mMovieDao.callDetailMovie(id)

    fun callListTvShow() : Flow<List<MovieEntity>> = mMovieDao.callListTvShow()

    fun callDetailTvShow(id : Int) : Flow<DetailMovieEntity> = mMovieDao.callDetailTvShow(id)

    suspend fun callMovie(movie : List<MovieEntity>){
        withContext(Dispatchers.IO){
            mMovieDao.insert(movie)
        }
    }

    suspend fun callDetailMovie(detail : DetailMovieEntity){
        withContext(Dispatchers.IO){
            mMovieDao.insertDetail(detail)
        }
    }

    fun callFavoriteMovie() : Flow<List<MovieEntity>> = mMovieDao.callFavoriteMovieList()

    fun callFavoriteTvShow() : Flow<List<MovieEntity>> = mMovieDao.callFavoriteTvShowList()

    fun ifFavoriteMovie(status : Int) : Flow<Boolean> = mMovieDao.ifMovieFavorite(status)

    suspend fun addFavoriteMovie(id : Int){
        withContext(Dispatchers.IO){
            mMovieDao.update(id)
        }
    }

    suspend fun deleteFavoriteMovie(id : Int){
        withContext(Dispatchers.IO) {
            mMovieDao.delete(id)
        }
    }
}