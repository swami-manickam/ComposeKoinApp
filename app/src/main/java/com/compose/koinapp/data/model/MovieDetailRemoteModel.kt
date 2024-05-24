package com.compose.koinapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailRemoteModel(
    @SerializedName("adult") var adult: Boolean?,
    @SerializedName("backdrop_path") var backdrop_path: String?,
    @SerializedName("belongs_to_collection") var belongs_to_collection: String?,
    @SerializedName("budget") var budget: Int?,
    @SerializedName("genres") var genres: List<GenresModel>?,
    @SerializedName("homepage") var homepage: String?,
    @SerializedName("id") var id: Int?,
    @SerializedName("imdb_id") var imdb_id: String?,
    @SerializedName("origin_country") var origin_country: List<String>?,
    @SerializedName("original_language") var original_language: String?,
    @SerializedName("original_title") var original_title: String?,
    @SerializedName("overview") var overview: String?,
    @SerializedName("popularity") var popularity: Double?,
    @SerializedName("poster_path") var posterPath: String?,
    @SerializedName("production_companies") var production_companies: List<ProductionCompaniesModel>?,
    @SerializedName("production_countries") var production_countries: List<ProductionCountriesModel>?,
    @SerializedName("release_date") var release_date: String?,
    @SerializedName("revenue") var revenue: Int?,
    @SerializedName("runtime") var runtime: Int?,
    @SerializedName("spoken_languages") var spoken_languages: List<SpokenLanguagesModel>?,
    @SerializedName("status") var status: String?,
    @SerializedName("tagline") var tagline: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("video") var video: Boolean?,
    @SerializedName("vote_average") var vote_average: Double?,
    @SerializedName("vote_count") var vote_count: Int?
)

data class GenresModel(
    @SerializedName("id") var id: Int?,
    @SerializedName("name") var name: String? 
)

data class ProductionCompaniesModel(
    @SerializedName("id") var id: Int? ,
    @SerializedName("logo_path") var logo_path: String? ,
    @SerializedName("name") var name: String? ,
    @SerializedName("origin_country") var origin_country: String?
)

data class ProductionCountriesModel(
    @SerializedName("iso_3166_1") var iso_3166_1: String? ,
    @SerializedName("name") var name: String? 
)


data class SpokenLanguagesModel(
    @SerializedName("english_name") var english_name: String? ,
    @SerializedName("iso_639_1") var iso_639_1: String? ,
    @SerializedName("name") var name: String? 
)



