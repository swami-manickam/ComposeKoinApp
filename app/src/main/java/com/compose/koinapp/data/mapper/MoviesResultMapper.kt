package com.compose.koinapp.data.mapper

import com.compose.koinapp.ApiConstants
import com.compose.koinapp.domain.entities.MoviesResultDataList
import com.compose.koinapp.domain.entities.MoviesResultList
import com.compose.koinapp.data.model.MovieResultRemoteModel
import com.compose.koinapp.data.model.MoviesRemoteModel


fun MoviesRemoteModel.toMovieResultModel():
        MoviesResultList {
    return MoviesResultList(
        page = this.page,
        results = results?.toResultPayloadPojo(),
        totalPages = totalPages,
        totalResults = totalResults
    )

}

fun MovieResultRemoteModel.toResultPojo() = MoviesResultDataList(
    adult = this.adult,
    backdropPath = this.backdrop_path,
    genreIds = this.genre_ids,
    id = this.id,
    originalLanguage = this.original_language,
    originalTitle = this.original_title,
    overview = this.overview,
    title = this.title,
    popularity = this.popularity,
    posterPath = this.poster_path?.let { "${ApiConstants.IMAGE_URL}$it" },
    releaseDate = this.release_date
)

fun Iterable<MovieResultRemoteModel>.toResultPayloadPojo(): List<MoviesResultDataList> =
    this.map { it.toResultPojo() }
