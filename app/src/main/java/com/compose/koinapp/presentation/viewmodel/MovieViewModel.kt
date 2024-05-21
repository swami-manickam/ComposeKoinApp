package com.compose.koinapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.compose.koinapp.data.MoviesRemoteModel
import com.compose.koinapp.data.Repository
import com.compose.koinapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository, application: Application) :
    BaseViewModel(application) {

    val _uiStateMovieList = MutableStateFlow<UiState<MoviesRemoteModel>>(UiState.Loading)
    val uiStateMovieList: StateFlow<UiState<MoviesRemoteModel>> = _uiStateMovieList



    fun getMoviesList() =viewModelScope.launch {
        repository.getMovies(context).collect(){
            when(it){
                is UiState.Loading ->{
                    _uiStateMovieList.value = UiState.Loading
                }
                is UiState.Success ->{
                    _uiStateMovieList.value = UiState.Success(it.data)
                }
                is UiState.Error -> {
                    _uiStateMovieList.value = UiState.Error(it.message)
                }

                else -> {}
            }
        }
    }


}