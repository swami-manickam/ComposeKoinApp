package com.compose.koinapp.di


import com.compose.koinapp.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MovieViewModel(get(),get())
    }
}