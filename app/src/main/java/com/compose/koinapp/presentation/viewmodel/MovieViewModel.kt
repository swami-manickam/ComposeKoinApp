package com.compose.koinapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.compose.koinapp.domain.Repository
import com.compose.koinapp.domain.entities.MovieDetail
import com.compose.koinapp.domain.entities.MoviesResultDataList
import com.compose.koinapp.domain.entities.MoviesResultList
import com.compose.koinapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository, application: Application) :
    BaseViewModel(application) {

    val _uiStateMovieList = MutableStateFlow<UiState<MoviesResultList>>(UiState.Loading)
    val uiStateMovieList: StateFlow<UiState<MoviesResultList>> = _uiStateMovieList


    val _movieDetailState = MutableStateFlow<UiState<MovieDetail>>(UiState.Loading)
    val movieDetailState: StateFlow<UiState<MovieDetail>> = _movieDetailState


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