package com.themovieguide.data.sources.local.mapper

import com.themovieguide.data.sources.local.model.RatedTvDB
import com.themovieguide.data.utils.castToJson
import com.themovieguide.data.utils.castToList
import com.themovieguide.domain.model.television.LiveVision
import com.themovieguide.domain.utils.EMPTY

fun LiveVision.castToTelevisionDB(): RatedTvDB {
    return RatedTvDB(
        backdropPath = this.backdropPath,
        firstAirDate = this.firstAirDate,
        genreIds = this.genreIds.castToJson(),
        id = this.id,
        name = this.name,
        originCountry = this.originCountry.castToJson(),
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun RatedTvDB.castToTelevision(): LiveVision {
    val genres = if (this.genreIds?.isNotEmpty() == true) this.genreIds.castToList<Int>() else emptyList()
    val orgCountry = if (this.originCountry?.isNotEmpty() == true) this.originCountry.castToList<String>() else emptyList()
    return LiveVision(
        backdropPath = this.backdropPath,
        firstAirDate = this.firstAirDate,
        genreIds = genres,
        id = this.id,
        name = EMPTY,
        originCountry = orgCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}