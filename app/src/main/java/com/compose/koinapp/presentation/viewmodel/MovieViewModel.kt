package com.compose.koinapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.compose.koinapp.data.model.MoviesRemoteModel
import com.compose.koinapp.data.Repository
import com.compose.koinapp.data.entities.MoviesResultData
import com.compose.koinapp.data.entities.MoviesResultDetail
import com.compose.koinapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository, application: Application) :
    BaseViewModel(application) {

    val _uiStateMovieList = MutableStateFlow<UiState<MoviesResultDetail>>(UiState.Loading)
    val uiStateMovieList: StateFlow<UiState<MoviesResultDetail>> = _uiStateMovieList


    val _movieDetailState = MutableStateFlow<UiState<MoviesResultData>>(UiState.Loading)
    val movieDetailState: StateFlow<UiState<MoviesResultData>> = _movieDetailState


    fun getMoviesList() = viewModelScope.launch {
        repository.getMovies(context).collect() {
            when (it) {
                is UiState.Loading -> {
                    _uiStateMovieList.value = UiState.Loading
                }

                is UiState.Success -> {
                    _uiStateMovieList.value = UiState.Success(it.data)
                }

                is UiState.Error -> {
                    _uiStateMovieList.value = UiState.Error(it.message)
                }

                else -> {}
            }
        }
    }

    fun getMovieDetailById(id: Int) = viewModelScope.launch {
        repository.getMovieDetail(context, id).collect {
            when (it) {
                is UiState.Loading -> {
                    _movieDetailState.value = UiState.Loading
                }

                is UiState.Success -> {
                    _movieDetailState.value = UiState.Success(it.data)
                }

                is UiState.Error -> {
                    _movieDetailState.value = UiState.Error(it.message)
                }
            }
        }
    }


}