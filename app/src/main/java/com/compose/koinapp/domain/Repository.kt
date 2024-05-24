package com.compose.koinapp.domain

import android.content.Context
import com.compose.koinapp.data.mapper.toMovieDetailModel
import com.compose.koinapp.data.mapper.toMovieResultModel
import com.compose.koinapp.data.remote.RemoteDataSource
import com.compose.koinapp.data.remote.toTransformResultFlow
import com.compose.koinapp.domain.entities.MovieDetail
import com.compose.koinapp.domain.entities.MoviesResultList
import com.compose.koinapp.utils.UiState
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getMovies(context: Context): Flow<UiState<MoviesResultList>> {
        return toTransformResultFlow(context, {
            remoteDataSource.getMovies()
        }){
            response -> response.toMovieResultModel()
        }
    }


    suspend fun getMovieDetail(context: Context, movieId: Int) : Flow<UiState<MovieDetail>> {
        return toTransformResultFlow(context,{
            remoteDataSource.getMovieDetail(movieId)
        }){
            response -> response.toMovieDetailModel()
        }

    }


}