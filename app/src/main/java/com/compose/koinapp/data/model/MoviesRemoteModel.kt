package com.compose.koinapp.data.model

import kotlinx.serialization.SerialName


data class MoviesRemoteModel(
    @SerialName("page")
    var page: Int? = null,
    @SerialName("results")
    var results: List<MovieResultRemoteModel>? = null,
    @SerialName("total_pages")
    var totalPages: Int? = null,
    @SerialName("total_results")
    var totalResults: Int? = null,
)

data class MovieResultRemoteModel(
    @SerialName("adult")
    var adult: Boolean? = null,
    @SerialName("backdrop_path")
    var backdrop_path: String? = null,
    @SerialName("genre_ids")
    var genre_ids: List<Int>? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("original_language")
    var original_language: String? = null,
    @SerialName("original_title")
    var original_title: String? = null,
    @SerialName("overview")
    var overview: String? = null,
    @SerialName("popularity")
    var popularity: Double? = null,
    @SerialName("poster_path")
    var poster_path: String? = null,
    @SerialName("release_date")
    var release_date: String? = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("video")
    var video: Boolean? = null,
    @SerialName("vote_average")
    var vote_average: Double? = null,
    @SerialName("vote_count")
    var vote_count: Int? = null,
)