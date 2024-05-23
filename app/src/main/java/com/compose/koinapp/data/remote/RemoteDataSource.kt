package com.compose.koinapp.data.remote

import com.compose.koinapp.ApiConstants

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getMovies() =
        apiService.getMovies(ApiConstants.AUTH_TOKEN, ApiConstants.API_KEY_VALUE, 3)

    suspend fun getMovieDetail(movieId: Int) = apiService.getMovieDetail(ApiConstants.AUTH_TOKEN,
       movieId)

}