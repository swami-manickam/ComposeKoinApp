package com.mycompose.android.koin.di

import com.compose.koinapp.data.remote.RemoteDataSource
import org.koin.dsl.module


val remoteDataSourceModule = module {
    factory {
        RemoteDataSource(get())
    }
}