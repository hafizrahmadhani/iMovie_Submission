package com.hafizrahmadhani.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovie
import com.hafizrahmadhani.core.domain.usecase.MovieUseCase

class ViewModelFavoriteMovie(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val notifMessage = MutableLiveData<String>()

    fun callNotifMessage(): LiveData<String> = notifMessage

    fun callFavoriteMovie(): LiveData<List<DataModelMovie>> =
        Transformations.map(movieUseCase.callMovieFavorite()) {
            val messageData = if (it.isEmpty()) "Tidak Ada Movie Favorite" else ""
            notifMessage.postValue(messageData)
            it
        }

    fun callFavoriteTvShow(): LiveData<List<DataModelMovie>> =
        Transformations.map(movieUseCase.callTvShowFavorite()) {
            val messageData = if (it.isEmpty()) "Tidak Ada Tv Show Favorite" else ""
            notifMessage.postValue(messageData)
            it
        }

}