package com.hafizrahmadhani.favorite.di

import com.hafizrahmadhani.favorite.viewmodel.ViewModelFavoriteMovie
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { ViewModelFavoriteMovie(get()) }
}