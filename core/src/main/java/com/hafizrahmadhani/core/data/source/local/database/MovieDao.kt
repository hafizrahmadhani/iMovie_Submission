package com.hafizrahmadhani.core.data.source.local.database

import androidx.room.*
import com.hafizrahmadhani.core.data.source.local.entity.DetailMovieEntity
import com.hafizrahmadhani.core.utils.TypeMovie
import com.hafizrahmadhani.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("select * from movie where type = ${TypeMovie.typeMovie}")
    fun callListMovie() : Flow<List<MovieEntity>>

    @Query("select * from detail where id = :id and type = ${TypeMovie.typeMovie}")
    fun callDetailMovie(id : Int): Flow<DetailMovieEntity>

    @Query("select * from movie where type = ${TypeMovie.typeTvShow}")
    fun callListTvShow() : Flow<List<MovieEntity>>

    @Query("select * from detail where id = :id and type = ${TypeMovie.typeTvShow}")
    fun callDetailTvShow(id : Int): Flow<DetailMovieEntity>

    @Query("select * from movie where type = ${TypeMovie.typeMovie} and status = 1")
    fun callFavoriteMovieList() : Flow<List<MovieEntity>>

    @Query("select * from movie where type = ${TypeMovie.typeTvShow} and status = 1")
    fun callFavoriteTvShowList() : Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDetail(detail : DetailMovieEntity)

    @Query("update movie set status = 1 where id = :movie")
    fun update(movie: Int)

    @Query("update movie set status = 0 where id = :movie")
    fun delete(movie: Int)

    @Query("select status == 1 from movie where id = :id")
    fun ifMovieFavorite(id: Int) : Flow<Boolean>
}