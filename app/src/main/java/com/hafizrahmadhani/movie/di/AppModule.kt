package com.hafizrahmadhani.movie.di

import com.hafizrahmadhani.movie.viewmodel.ViewModelDetailMovie
import com.hafizrahmadhani.movie.viewmodel.ViewModelMovie
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ViewModelMovie(get()) }
    viewModel { ViewModelDetailMovie(get()) }
}