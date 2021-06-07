package com.hafizrahmadhani.core.data.source.remote.network

import com.hafizrahmadhani.core.BuildConfig
import com.hafizrahmadhani.core.data.source.remote.response.MovieDetailResponse
import com.hafizrahmadhani.core.data.source.remote.response.MovieResponse
import com.hafizrahmadhani.core.data.source.remote.response.TvResponse
import retrofit2.http.GET
import retrofit2.http.Path

const val movieApi : String = BuildConfig.MOVIE_API

interface ApiDataSource {

    @GET("movie/now_playing?api_key=$movieApi")
    suspend fun callMovieList(): MovieResponse

    @GET("movie/{movie_id}?api_key=$movieApi")
    suspend fun callMovieDetail(@Path("movie_id") movieId: Int) : MovieDetailResponse

    @GET("tv/popular?api_key=$movieApi")
    suspend fun callTvShowList() : TvResponse

    @GET("tv/{tv_id}?api_key=$movieApi")
    suspend fun callTvShowDetail(@Path("tv_id") tvId: Int) : MovieDetailResponse

}