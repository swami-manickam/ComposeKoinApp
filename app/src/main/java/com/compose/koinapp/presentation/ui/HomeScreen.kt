package com.compose.koinapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.compose.koinapp.data.entities.MoviesResultData
import com.compose.koinapp.data.entities.MoviesResultDetail
import com.compose.koinapp.presentation.ui.components.CustomToolbarScreen
import com.compose.koinapp.presentation.ui.components.ProgressLoader
import com.compose.koinapp.presentation.viewmodel.MovieViewModel
import com.compose.koinapp.utils.Routes.getSecondScreenPath
import com.compose.koinapp.utils.UiState


@Composable
fun HomeScreen(navigation: NavController, mainViewModel: MovieViewModel) {
    Scaffold(
        topBar = {
            CustomToolbarScreen(navController = navigation, title = "Home", false)
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //add your code
            LaunchedEffect(key1 = Unit) {
                getMovieListAPI(mainViewModel)
            }
            val state = mainViewModel.uiStateMovieList.collectAsState()
            when (state.value) {
                is UiState.Success -> {
                    ProgressLoader(isLoading = false)
                    (state.value as UiState.Success<MoviesResultDetail>).data?.let {
                        it.results?.let { it1 ->
                            MovieList(movies = it1) { recipe ->
                                navigation.navigate(getSecondScreenPath(recipe.id))
                            }
                        }
                    }
                }

                is UiState.Loading -> {
                    ProgressLoader(isLoading = true)
                }

                is UiState.Error -> {
                    ProgressLoader(isLoading = false)
                    //Handle Error
                }
            }
        }
    }


}

@Composable
fun MovieListCard(movie: MoviesResultData, onMovieClick: (MoviesResultData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onMovieClick(movie) },
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(movie.posterPath),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = movie.title ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Type: ${movie.popularity}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Language: ${movie.originalLanguage}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Release Date: ${movie.releaseDate}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun MovieList(
    movies: List<MoviesResultData>,
    onMovieClick: (MoviesResultData) -> Unit
) {
    LazyColumn {
        items(movies) { movie ->
            MovieListCard(movie = movie, onMovieClick = onMovieClick)
        }
    }
}

// Call the function to fetch movie list
private fun getMovieListAPI(mainViewModel: MovieViewModel) {
    mainViewModel.getMoviesList()
}