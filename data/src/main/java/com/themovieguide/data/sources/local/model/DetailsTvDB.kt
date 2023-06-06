package com.themovieguide.data.sources.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.themovieguide.data.utils.EMPTY

@Entity(tableName = DetailsTvDB.TABLE_NAME_MOVIE)
data class DetailsTvDB(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0,
    val adult: Boolean? = false,
    val backdropPath: String? = EMPTY,
    val createdBy: String? = EMPTY,
    val episodeRunTime: String? = EMPTY,
    val firstAirDate: String? = EMPTY,
    val genres: String? = EMPTY,
    val homepage: String? = EMPTY,
    val id: Int? = 0,
    val inProduction: Boolean? = false,
    val languages: String? = EMPTY,
    val lastAirDate: String? = EMPTY,
    val lastEpisodeToAir: String? = EMPTY,
    val name: String? = EMPTY,
    val networks: String? = EMPTY,
    val nextEpisodeToAir: String? = EMPTY,
    val numberOfEpisodes: Int? = 0,
    val numberOfSeasons: Int? = 0,
    val originCountry: String? = EMPTY,
    val originalLanguage: String? = EMPTY,
    val originalName: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val posterPath: String? = EMPTY,
    val productionCompanies: String? = EMPTY,
    val productionCountries: String = EMPTY,
    val seasons: String? = EMPTY,
    val spokenLanguages: String? = EMPTY,
    val status: String? = EMPTY,
    val tagline: String? = EMPTY,
    val type: String? = EMPTY,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
) {
    constructor() : this(
        adult = false,
        backdropPath = EMPTY,
        createdBy = EMPTY,
        episodeRunTime = EMPTY,
        firstAirDate = EMPTY,
        genres = EMPTY,
        homepage = EMPTY,
        id = 0,
        inProduction = false,
        languages = EMPTY,
        lastAirDate = EMPTY,
        lastEpisodeToAir = EMPTY,
        name = EMPTY,
        networks = EMPTY,
        nextEpisodeToAir = EMPTY,
        numberOfEpisodes = 0,
        numberOfSeasons = 0,
        originCountry = EMPTY,
        originalLanguage = EMPTY,
        originalName = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        posterPath = EMPTY,
        productionCompanies = EMPTY,
        productionCountries = EMPTY,
        seasons = EMPTY,
        spokenLanguages = EMPTY,
        status = EMPTY,
        tagline = EMPTY,
        type = EMPTY,
        voteAverage = 0.0,
        voteCount = 0,
    )
    companion object {
        const val TABLE_NAME_MOVIE = "details_tv"
    }
}