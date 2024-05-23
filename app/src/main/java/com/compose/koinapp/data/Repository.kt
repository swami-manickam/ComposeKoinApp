package com.compose.koinapp.data

import android.content.Context
import com.compose.koinapp.data.entities.MoviesResultData
import com.compose.koinapp.data.entities.MoviesResultDetail
import com.compose.koinapp.data.mapper.toMovieResultModel
import com.compose.koinapp.data.mapper.toResultPojo
import com.compose.koinapp.data.model.MovieResultRemoteModel
import com.compose.koinapp.data.model.MoviesRemoteModel
import com.compose.koinapp.data.remote.RemoteDataSource
import com.compose.koinapp.data.remote.toResultFlow
import com.compose.koinapp.data.remote.toTransformResultFlow
import com.compose.koinapp.utils.UiState
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource) {


    suspend fun getMovies(context: Context): Flow<UiState<MoviesResultDetail>> {
        return toTransformResultFlow(context, {
            remoteDataSource.getMovies()
        }){
            response -> response.toMovieResultModel()
        }
    }


    suspend fun getMovieDetail(context: Context, movieId: Int) : Flow<UiState<MoviesResultData>> {
        return toTransformResultFlow(context,{
            remoteDataSource.getMovieDetail(movieId)
        }){
            response -> response.toResultPojo()
        }

    }


}