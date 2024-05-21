package com.compose.koinapp.data.remote

import com.mycompose.android.koin.data.remote.ApiService

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getMovies() = apiService.getMovies()
    suspend fun getMovieDetail(movieId:Int) = apiService.getMovieDetail(movieId)

}