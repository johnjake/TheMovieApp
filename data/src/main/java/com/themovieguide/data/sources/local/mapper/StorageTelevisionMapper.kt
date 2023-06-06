package com.themovieguide.data.sources.local.mapper

import com.themovieguide.data.model.meta.television.DetailsTelevisionMeta
import com.themovieguide.data.sources.local.model.DetailsTvDB
import com.themovieguide.data.sources.local.model.DiscoverDB
import com.themovieguide.data.sources.local.model.RatedTvDB
import com.themovieguide.data.sources.local.model.SearchTvDB
import com.themovieguide.data.sources.local.model.TodayAirDB
import com.themovieguide.data.sources.local.model.TrendingDB
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

fun LiveVision.castToTelevisionSearchDB(): SearchTvDB {
    return SearchTvDB(
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

fun LiveVision.castToTodayAirDB(): TodayAirDB {
    return TodayAirDB(
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

fun LiveVision.castDiscoverDB(): DiscoverDB {
    return DiscoverDB(
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

fun LiveVision.castTrendingDB(): TrendingDB {
    return TrendingDB(
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

fun DetailsTelevisionMeta.castToDetailsDB(): DetailsTvDB {
    return DetailsTvDB(
        adult = this.adult,
        backdropPath = this.backdrop_path,
        createdBy = this.created_by?.castToJson(),
        episodeRunTime = this.episode_run_time.castToJson(),
        firstAirDate = this.first_air_date,
        genres = this.genres.castToJson(),
        homepage = this.homepage,
        id = this.id,
        inProduction = this.in_production,
        languages = this.languages?.castToJson(),
        lastAirDate = this.last_air_date,
        lastEpisodeToAir = this.last_episode_to_air.castToJson(),
        name = this.name,
        networks = this.networks.castToJson(),
        nextEpisodeToAir = this.next_episode_to_air.castToJson(),
        numberOfEpisodes = this.number_of_episodes,
        numberOfSeasons = this.number_of_seasons,
        originCountry = this.origin_country.castToJson(),
        originalLanguage = this.original_language,
        originalName = this.original_name,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.poster_path,
        productionCompanies = this.production_companies.castToJson(),
        productionCountries = this.production_countries.castToJson(),
        seasons = this.seasons.castToJson(),
        spokenLanguages = this.spoken_languages.castToJson(),
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
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

fun SearchTvDB.castToTelevisionSearch(): LiveVision {
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
