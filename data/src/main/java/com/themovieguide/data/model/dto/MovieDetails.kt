package com.themovieguide.data.model.dto

import android.os.Parcelable
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetails(
    val adult: Boolean? = false,
    val backdrop_path: String? = EMPTY,
    val belongs_to_collection: String? = EMPTY,
    val budget: Int? = 0,
    val genres: List<Genre>? = emptyList(),
    val homepage: String? = EMPTY,
    val id: Int? = 0,
    val imdb_id: String? = EMPTY,
    val original_language: String? = EMPTY,
    val original_title: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val poster_path: String? = EMPTY,
    val production_companies: List<ProductionCompany>? = emptyList(),
    val production_countries: List<ProductionCountry>? = emptyList(),
    val release_date: String? = EMPTY,
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val spoken_languages: List<SpokenLanguage>? = emptyList(),
    val status: String? = EMPTY,
    val tagline: String? = EMPTY,
    val title: String? = EMPTY,
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,
) : Parcelable
