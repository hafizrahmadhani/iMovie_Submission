package com.hafizrahmadhani.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.core.domain.datamodel.DataModelMovie
import com.hafizrahmadhani.core.domain.usecase.MovieUseCase

class ViewModelFavoriteMovie (private val useCase: MovieUseCase) : ViewModel(){

    private val notifMessage = MutableLiveData<String>()

    fun callNotifMessage() : LiveData<String> = notifMessage

    fun callFavoriteMovie(): LiveData<List<DataModelMovie>> {
        val favorite = useCase.callMovieFavorite()
        favorite.observeForever{
            val messageData = if(it.isEmpty()) "Tidak Ada Movie Favorite" else ""
            notifMessage.postValue(messageData)
        }
        return favorite
    }

    fun callFavoriteTvShow(): LiveData<List<DataModelMovie>> {
        val favorite = useCase.callTvShowFavorite()
        favorite.observeForever{
            val messageData = if(it.isEmpty()) "Tidak Ada Tv Show Favorite" else ""
            notifMessage.postValue(messageData)
        }
        return favorite
    }
}