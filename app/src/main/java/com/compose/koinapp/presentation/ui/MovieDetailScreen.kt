package com.compose.koinapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.compose.koinapp.domain.entities.MovieDetail
import com.compose.koinapp.presentation.ui.components.CustomToolbarScreen
import com.compose.koinapp.presentation.ui.components.ProgressLoader
import com.compose.koinapp.presentation.viewmodel.MovieViewModel
import com.compose.koinapp.utils.UiState


@Composable
fun MovieDetailScreen(navController: NavController, mainViewModel: MovieViewModel, id: Int) {


    val scrollState = rememberScrollState()
    var titleState by remember { mutableStateOf("Movie Detail") }

    Scaffold(
        topBar = {
            CustomToolbarScreen(navController = navController, title = titleState, true)
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //add your code
            LaunchedEffect(key1 = Unit) {
                getMovieDetails(mainViewModel, id)
            }
            val state = mainViewModel.movieDetailState.collectAsState()
            when (state.value) {
                is UiState.Success -> {
                    ProgressLoader(isLoading = false)
                    (state.value as UiState.Success<MovieDetail>).data?.let {
                        //MovieInfoView(it)
                        MovieDetailView(it,navController)
                        titleState = it.title.toString()
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


private fun getMovieDetails(mainViewModel: MovieViewModel, id: Int) {
    mainViewModel.getMovieDetailById(id)
}


@Composable
fun MovieInfoView(result: MovieDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(result.poster_path),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = result.title ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Overview:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Gray
        )
        /*Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            .forEach { $desc ->
                Text(
                    text = "• $desc",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }*/
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Description:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Gray
        )
        /*Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            ?.forEachIndexed { index, $desc ->
                Text(
                    text = "${index + 1}. $desc",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }*/
    }
}

@Composable
fun MovieDetailView(details :MovieDetail,navController: NavController) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            BackGroundPoster(details = details, navController)
            ForegroundPoster(details = details)
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = details.title?:"",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 38.sp,
                color = Color.White,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
            Rating(details = details, modifier = Modifier)
            details.overview?.let { TextBuilder(icon = Icons.Filled.Info, title = "Overview:", bodyText = it) }
            TextBuilder(
                icon = Icons.Filled.Person,
                title = "Popularity:",
                bodyText = details.popularity.toString()
            )
            ImageRow(details = details)
        }
}

// Call the function to fetch movies



@Composable
fun ImageRow(details: MovieDetail) {
    if (details.production_companies?.isNotEmpty() == true) {
        LazyRow {
            items(details.production_companies.size) {
                AsyncImage(
                    model = details.production_companies[it].logo_path, contentDescription = "",
                    Modifier
                        .padding(6.dp)
                        .height(70.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun TextBuilder(icon: ImageVector, title: String, bodyText: String) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = "Person",
            tint = Color.White
        )
        Text(
            text = title,
            Modifier.padding(start = 10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
    Text(text = bodyText, color = Color.White)
}

@Composable
fun Rating(details: MovieDetail, modifier: Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Icon(imageVector = Icons.Filled.Star, contentDescription = "", tint = Color.White)
        Text(
            text = details.vote_count.toString(),
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier = modifier.width(25.dp))
        Icon(
            painter = painterResource(id = androidx.core.R.drawable.ic_call_decline),
            contentDescription = "",
            tint = Color.White
        )
        Text(
            text = details.runtime.toString(),
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier = modifier.width(25.dp))
        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "", tint = Color.White)
        Text(
            text = details.status.toString(),
            modifier.padding(start = 6.dp),
            color = Color.White
        )
    }
}

@Composable
fun ForegroundPoster(details: MovieDetail) {

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 40.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {

        AsyncImage(
            model = details.poster_path, contentDescription = details.title,
            Modifier
                .width(280.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        /*Box(
            modifier = Modifier
                .matchParentSize()
                .width(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xB91A1B1B),
                        )
                    ), shape = RoundedCornerShape(16.dp)
                )
        )*/
    }
}

@Composable
fun BackGroundPoster(details: MovieDetail, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            /*.background(Color.DarkGray)*/
    ) {


        AsyncImage(
            model = details.backdrop_path, contentDescription = details.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .height(280.dp)
                .alpha(0.6f),
            contentScale = ContentScale.Crop
        )


        /*Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null, tint = Color.White,
            modifier = Modifier
                .size(70.dp)
                .padding(start = 20.dp, top = 40.dp)
                .align(Alignment.TopStart)
                .clickable {
                    navController.popBackStack()
                })*/



        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.DarkGray
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }

}
