package com.themovieguide.data.model.meta

import android.os.Parcelable
import com.themovieguide.data.model.dto.BelongsToCollection
import com.themovieguide.data.model.dto.Genre
import com.themovieguide.data.model.dto.ProductionCompany
import com.themovieguide.data.model.dto.ProductionCountry
import com.themovieguide.data.model.dto.SpokenLanguage
import com.themovieguide.data.model.dto.Videos
import com.themovieguide.data.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsMeta(
    val adult: Boolean? = false,
    val backdrop_path: String? = EMPTY,
    val belongs_to_collection: BelongsToCollection? = BelongsToCollection(),
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
    val videos: Videos? = Videos(),
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,
) : Parcelable {
    constructor() : this(
        adult = false,
        backdrop_path = EMPTY,
        belongs_to_collection = BelongsToCollection(),
        budget = 0,
        genres = emptyList(),
        homepage = EMPTY,
        id = 0,
        imdb_id = EMPTY,
        original_language = EMPTY,
        original_title = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        poster_path = EMPTY,
        production_companies = emptyList(),
        production_countries = emptyList(),
        release_date = EMPTY,
        revenue = 0,
        runtime = 0,
        spoken_languages = emptyList(),
        status = EMPTY,
        tagline = EMPTY,
        title = EMPTY,
        videos = Videos(),
        vote_average = 0.0,
        vote_count = 0,
    )
}
