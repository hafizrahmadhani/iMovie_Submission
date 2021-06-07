package com.hafizrahmadhani.core.data

import com.hafizrahmadhani.core.data.source.local.LocalDataSource
import com.hafizrahmadhani.core.data.source.remote.RemoteDataSource
import com.hafizrahmadhani.core.data.source.remote.network.ApiResponse
import com.hafizrahmadhani.core.data.source.remote.response.*
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovie
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovieDetail
import com.hafizrahmadhani.core.domain.repository.IMovieRepository
import com.hafizrahmadhani.core.utils.DataMapper
import com.hafizrahmadhani.core.utils.TypeMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMovieRepository {
    override fun callMovieList(): Flow<Resource<List<DataModelMovie>>> =
        object : NetworkBoundResource<List<DataModelMovie>, MovieResponse>(){
            override fun loadFromDB(): Flow<List<DataModelMovie>> {
                return localDataSource.callListMovie().map { DataMapper.movieEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<DataModelMovie>?) =  data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> = remoteDataSource.callMovieList()

            override suspend fun saveCallResult(data: MovieResponse) {
                val datamapper = DataMapper.movieResponseToEntity(data)
                localDataSource.callMovie(datamapper)
            }
        }.asFlow()


    override fun callDetailMovie(idMovie: Int): Flow<Resource<DataModelMovieDetail>> =
        object : NetworkBoundResource<DataModelMovieDetail, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<DataModelMovieDetail> =
                localDataSource.callDetailMovie(idMovie).map {
                    if (it != null) {
                        DataMapper.detailEntityToDomain(it)
                    } else {
                        it
                    }
                }

            override fun shouldFetch(data: DataModelMovieDetail?) = data?.id == null

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.callMovieDetail(idMovie)

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                val dataMapper = DataMapper.detailResponseToEntity(data, TypeMovie.typeMovie)
                localDataSource.callDetailMovie(dataMapper)
            }
        }.asFlow()


    override fun callTvShowList(): Flow<Resource<List<DataModelMovie>>> =
        object : NetworkBoundResource<List<DataModelMovie>, TvResponse>(){
            override fun loadFromDB(): Flow<List<DataModelMovie>> {
                return localDataSource.callListTvShow().map { DataMapper.movieEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<DataModelMovie>?) = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<TvResponse>> = remoteDataSource.callTvList()

            override suspend fun saveCallResult(data: TvResponse) {
                val dataMapper = DataMapper.tvShowResponseToEntity(data)
                localDataSource.callMovie(dataMapper)
            }
        }.asFlow()


    override fun callDetailTv(idTv: Int): Flow<Resource<DataModelMovieDetail>> =
        object : NetworkBoundResource<DataModelMovieDetail, MovieDetailResponse>(){
            override fun loadFromDB(): Flow<DataModelMovieDetail> =
                localDataSource.callDetailTvShow(idTv).map {
                    if(it != null){
                        DataMapper.detailEntityToDomain(it)
                    }else{
                        it
                    }
                }

            override fun shouldFetch(data: DataModelMovieDetail?)= data?.id == null

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> = remoteDataSource.callTvShowDetail(idTv)

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                val dataMapper = DataMapper.detailResponseToEntity(data, TypeMovie.typeTvShow)
                localDataSource.callDetailMovie(dataMapper)
            }
        }.asFlow()

    override suspend fun insertMovieFavorite(movie: Int) {
        localDataSource.addFavoriteMovie(movie)
    }

    override suspend fun deleteMovieFavorite(movie: Int) {
        localDataSource.deleteFavoriteMovie(movie)
    }

    override fun callMovieFavorite(): Flow<List<DataModelMovie>> {
        return localDataSource.callFavoriteMovie().map{
            DataMapper.movieEntityToDomain(it)
        }
    }

    override fun callTvShowFavorite(): Flow<List<DataModelMovie>> {
        return localDataSource.callFavoriteTvShow().map {
            DataMapper.movieEntityToDomain(it)
        }
    }

    override fun ifFavoriteMovie(id: Int): Flow<Boolean> {
        return localDataSource.ifFavoriteMovie(id)
    }

}