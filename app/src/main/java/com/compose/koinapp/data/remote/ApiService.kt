package com.compose.koinapp.data.remote

import com.compose.koinapp.ApiConstants
import com.compose.koinapp.ApiConstants.GET_MOVIES
import com.compose.koinapp.ApiConstants.GET_MOVIE_DETAIL
import com.compose.koinapp.data.model.MovieDetailRemoteModel
import com.compose.koinapp.data.model.MoviesRemoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(GET_MOVIES)
    suspend fun getMovies(
        @Header(ApiConstants.AUTHORIZATION) authToken: String,
        @Query(ApiConstants.API_KEY) apiKey: String,
        @Query(ApiConstants.PAGE) page: Int
    ): Response<MoviesRemoteModel>

    @GET("$GET_MOVIE_DETAIL/{movieId}")
    suspend fun getMovieDetail(
        @Header(ApiConstants.AUTHORIZATION) authToken: String,
        @Path(ApiConstants.MOVIE_ID) movieId: Int): Response<MovieDetailRemoteModel>

}