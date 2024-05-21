package com.compose.koinapp.data

import android.content.Context
import com.compose.koinapp.data.remote.RemoteDataSource
import com.compose.koinapp.data.remote.toResultFlow
import com.compose.koinapp.utils.UiState
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource) {


    suspend fun getMovies(context: Context): Flow<UiState<MoviesRemoteModel>> {
        return toResultFlow(context) {
            remoteDataSource.getMovies()
        }
    }


    suspend fun getMovieDetail(context: Context, movieId: Int) : Flow<UiState<MovieResultRemoteModel>> {
        return toResultFlow(context){
            remoteDataSource.getMovieDetail(movieId)
        }

    }


}