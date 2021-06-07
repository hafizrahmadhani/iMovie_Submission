@file:Suppress("UNCHECKED_CAST")

package com.hafizrahmadhani.core.utils

import com.hafizrahmadhani.core.data.source.local.entity.DetailMovieEntity
import com.hafizrahmadhani.core.data.source.local.entity.MovieEntity
import com.hafizrahmadhani.core.data.source.remote.response.MovieDetailResponse
import com.hafizrahmadhani.core.data.source.remote.response.MovieResponse
import com.hafizrahmadhani.core.data.source.remote.response.TvResponse
import com.hafizrahmadhani.core.domain.datamodel.DataModelGenre
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovie
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovieDetail

object DataMapper {

    fun movieResponseToEntity(movie: MovieResponse) : List<MovieEntity> {
        val list = movie.movie
        return list.map {
            MovieEntity(it.id, it.title, it.overview, it.poster_path, it.vote_average, TypeMovie.typeMovie)
        }
    }

    fun tvShowResponseToEntity(tvShow : TvResponse) : List<MovieEntity>{
        val list = tvShow.tv_show
        return list.map { MovieEntity(it.id, it.name, it.overview, it.poster_path, it.vote_average, TypeMovie.typeTvShow) }
    }

    fun detailResponseToEntity(detail : MovieDetailResponse, type : Int) : DetailMovieEntity {
        val genre = detail.genres.map { it.name }
        return DetailMovieEntity(detail.id, detail.title, detail.overview, detail.poster_path, detail.backdrop_path, genre as List<String>, detail.release_date, detail.vote_average, type)
    }

    fun movieEntityToDomain(movie : List<MovieEntity>) : List<DataModelMovie>{
        return movie.map{ DataModelMovie(
            it.id, it.title, it.description, it.poster, it.vote, it.type, it.status)
        }
    }

    fun detailEntityToDomain(detail : DetailMovieEntity) : DataModelMovieDetail{
        val genre = detail.genre.map { DataModelGenre(it)}
        return DataModelMovieDetail(detail.id, detail.title, detail.description, detail.poster, detail.image, genre, detail.date, detail.voteAverage)
    }
}