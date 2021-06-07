package com.hafizrahmadhani.core.data.source.remote

import android.util.Log
import com.hafizrahmadhani.core.data.source.remote.network.ApiDataSource
import com.hafizrahmadhani.core.data.source.remote.network.ApiResponse
import com.hafizrahmadhani.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiDataSource: ApiDataSource){

    suspend fun callMovieList() : Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val list = apiDataSource.callMovieList()
                if(list.movie.isEmpty()){
                    emit(ApiResponse.Empty)
                }else{
                    emit(ApiResponse.Success(list))
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource Failed", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun callMovieDetail(id : Int) : Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val detail = apiDataSource.callMovieDetail(id)
                emit(ApiResponse.Success(detail))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource Failed", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun callTvList() : Flow<ApiResponse<TvResponse>> {
        return flow {
            try {
                val list = apiDataSource.callTvShowList()
                if (list.tv_show.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(list))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource Failed", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun callTvShowDetail(id : Int) : Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val detail = apiDataSource.callTvShowDetail(id)
                emit(ApiResponse.Success(detail))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource Failed", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}