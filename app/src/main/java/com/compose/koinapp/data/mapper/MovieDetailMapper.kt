package com.compose.koinapp.data.mapper

import com.compose.koinapp.ApiConstants
import com.compose.koinapp.data.model.GenresModel
import com.compose.koinapp.data.model.MovieDetailRemoteModel
import com.compose.koinapp.data.model.ProductionCompaniesModel
import com.compose.koinapp.data.model.ProductionCountriesModel
import com.compose.koinapp.data.model.SpokenLanguagesModel
import com.compose.koinapp.domain.entities.MovieDetail
import com.compose.koinapp.domain.entities.MovieGenres
import com.compose.koinapp.domain.entities.MovieProdCompany
import com.compose.koinapp.domain.entities.MovieProdCountry
import com.compose.koinapp.domain.entities.MovieSpokenLanguage


fun MovieDetailRemoteModel.toMovieDetailModel(): MovieDetail {
    return MovieDetail(
        adult = this.adult,
        backdrop_path = this.backdrop_path.let { "${ApiConstants.IMAGE_URL}$it" },
        belongs_to_collection = this.belongs_to_collection,
        budget = this.budget,
        genres = this.genres?.toGenrePayloadPojo(),
        homepage = this.homepage,
        id = this.id,
        imdb_id = this.imdb_id,
        origin_country = this.origin_country,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.posterPath.let { "${ApiConstants.IMAGE_URL}$it" },
        production_companies = this.production_companies?.toProdCompanyPayloadPojo(),
        production_countries = this.production_countries?.toProdCountryPayloadPojo(),
        release_date = this.release_date,
        revenue = this.revenue,
        runtime = this.runtime,
        spoken_languages = this.spoken_languages?.toSpokenPayloadPojo(),
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
    )

}


fun GenresModel.toGenrePojo() = MovieGenres(
    id = this.id,
    name = this.name
)

fun Iterable<GenresModel>.toGenrePayloadPojo(): List<MovieGenres> =
    this.map { it.toGenrePojo() }

fun ProductionCompaniesModel.toProdCompanyPojo() = MovieProdCompany(
    id = this.id,
    logo_path = this.logo_path.let { "${ApiConstants.IMAGE_URL}$it" },
    name = this.name,
    origin_country = this.origin_country
)

fun Iterable<ProductionCompaniesModel>.toProdCompanyPayloadPojo(): List<MovieProdCompany> =
    this.map { it.toProdCompanyPojo() }

fun ProductionCountriesModel.toProdCountryPojo() = MovieProdCountry(
    iso_3166_1 = this.iso_3166_1,
    name = this.name
)

fun Iterable<ProductionCountriesModel>.toProdCountryPayloadPojo(): List<MovieProdCountry> =
    this.map { it.toProdCountryPojo() }


fun SpokenLanguagesModel.toSpokenPojo() = MovieSpokenLanguage(
    english_name = this.english_name,
    iso_639_1 = this.iso_639_1,
    name = this.name
)

fun Iterable<SpokenLanguagesModel>.toSpokenPayloadPojo(): List<MovieSpokenLanguage> =
    this.map { it.toSpokenPojo() }