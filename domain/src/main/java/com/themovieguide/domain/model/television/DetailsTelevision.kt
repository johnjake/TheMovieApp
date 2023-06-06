package com.themovieguide.domain.model.television

import com.themovieguide.domain.model.GenreEntity
import com.themovieguide.domain.model.LanguageEntity
import com.themovieguide.domain.model.ProductionCompanyEntity
import com.themovieguide.domain.model.ProductionCountryEntity
import com.themovieguide.domain.utils.EMPTY

data class DetailsTelevision(
    val adult: Boolean? = false,
    val backdropPath: String? = EMPTY,
    val createdBy: List<CreatedEntity>? = emptyList(),
    val episodeRunTime: List<String>? = emptyList(),
    val firstAirDate: String? = EMPTY,
    val genres: List<GenreEntity>? = emptyList(),
    val homepage: String? = EMPTY,
    val id: Int? = 0,
    val inProduction: Boolean? = false,
    val languages: List<String>? = emptyList(),
    val lastAirDate: String? = EMPTY,
    val lastEpisodeToAir: LastEpisodeToAirEntity? = LastEpisodeToAirEntity(),
    val name: String? = EMPTY,
    val networks: List<NetworkEntity>? = emptyList(),
    val nextEpisodeToAir: NextEpisodeToAirEntity? = NextEpisodeToAirEntity(),
    val numberOfEpisodes: Int? = 0,
    val numberOfSeasons: Int? = 0,
    val originCountry: List<String>? = emptyList(),
    val originalLanguage: String? = EMPTY,
    val originalName: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val posterPath: String? = EMPTY,
    val productionCompanies: List<ProductionCompanyEntity>? = emptyList(),
    val productionCountries: List<ProductionCountryEntity>? = emptyList(),
    val seasons: List<SeasonEntity>? = emptyList(),
    val spokenLanguages: List<LanguageEntity>? = emptyList(),
    val status: String? = EMPTY,
    val tagline: String? = EMPTY,
    val type: String? = EMPTY,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
) {
    constructor() : this(
        adult = false,
        backdropPath = EMPTY,
        createdBy = emptyList(),
        episodeRunTime = emptyList(),
        firstAirDate = EMPTY,
        genres = emptyList(),
        homepage = EMPTY,
        id = 0,
        inProduction = false,
        languages = emptyList(),
        lastAirDate = EMPTY,
        lastEpisodeToAir = LastEpisodeToAirEntity(),
        name = EMPTY,
        networks = emptyList(),
        nextEpisodeToAir = NextEpisodeToAirEntity(),
        numberOfEpisodes = 0,
        numberOfSeasons = 0,
        originCountry = emptyList(),
        originalLanguage = EMPTY,
        originalName = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        posterPath = EMPTY,
        productionCompanies = emptyList(),
        productionCountries = emptyList(),
        seasons = emptyList(),
        spokenLanguages = emptyList(),
        status = EMPTY,
        tagline = EMPTY,
        type = EMPTY,
        voteAverage = 0.0,
        voteCount = 0,
    )
}