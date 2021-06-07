package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovie
import com.hafizrahmadhani.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class ViewModelDetailMovie (private val movieUseCase: MovieUseCase) : ViewModel() {

    fun callDetailMovie(idMovie : Int) = movieUseCase.callDetailMovie(idMovie)

    fun callDetailTvShow(idTv : Int) = movieUseCase.callDetailTv(idTv)

    fun insertMovieFavorite(favorite : DataModelMovie){
        viewModelScope.launch {
            movieUseCase.insertMovieFavorite(favorite.id!!)
        }
    }

    fun deleteMovieFavorite(favorite : DataModelMovie){
        viewModelScope.launch {
            movieUseCase.deleteMovieFavorite(favorite.id!!)
        }
    }

    fun ifFavoriteMovie(id : Int) : LiveData<Boolean>{
        return movieUseCase.ifFavoriteMovie(id)
    }

}