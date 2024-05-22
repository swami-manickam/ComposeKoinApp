package com.compose.koinapp.data.remote

import com.compose.koinapp.ApiConstants.GET_MOVIES
import com.compose.koinapp.ApiConstants.GET_MOVIE_DETAIL
import com.compose.koinapp.data.MovieResultRemoteModel
import com.compose.koinapp.data.MoviesRemoteModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(GET_MOVIES)
    suspend fun getMovies(): Response<MoviesRemoteModel>

    @GET(GET_MOVIE_DETAIL)
    suspend fun getMovieDetail(movieId: Int): Response<MovieResultRemoteModel>

}