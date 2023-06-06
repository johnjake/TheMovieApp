package com.themovieguide.data.mapper

import com.themovieguide.data.model.dto.television.details.CreatedBy
import com.themovieguide.data.model.dto.television.details.Genre
import com.themovieguide.data.model.dto.television.details.LastEpisodeToAir
import com.themovieguide.data.model.dto.television.details.Network
import com.themovieguide.data.model.dto.television.details.NextEpisodeToAir
import com.themovieguide.data.model.dto.television.details.ProductionCompany
import com.themovieguide.data.model.dto.television.details.ProductionCountry
import com.themovieguide.data.model.dto.television.details.Season
import com.themovieguide.data.model.dto.television.details.SpokenLanguage
import com.themovieguide.data.model.dto.television.rated.Television
import com.themovieguide.data.model.meta.television.DetailsTelevisionMeta
import com.themovieguide.data.sources.local.model.DetailsTvDB
import com.themovieguide.data.sources.local.model.DiscoverDB
import com.themovieguide.data.sources.local.model.RatedTvDB
import com.themovieguide.data.sources.local.model.SearchTvDB
import com.themovieguide.data.sources.local.model.TodayAirDB
import com.themovieguide.data.sources.local.model.TrendingDB
import com.themovieguide.data.utils.castToClass
import com.themovieguide.data.utils.castToList
import com.themovieguide.domain.model.GenreEntity
import com.themovieguide.domain.model.LanguageEntity
import com.themovieguide.domain.model.ProductionCompanyEntity
import com.themovieguide.domain.model.ProductionCountryEntity
import com.themovieguide.domain.model.television.CreatedEntity
import com.themovieguide.domain.model.television.DetailsTelevision
import com.themovieguide.domain.model.television.LastEpisodeToAirEntity
import com.themovieguide.domain.model.television.LiveVision
import com.themovieguide.domain.model.television.NetworkEntity
import com.themovieguide.domain.model.television.NextEpisodeToAirEntity
import com.themovieguide.domain.model.television.SeasonEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Television.castToLiveVision(): LiveVision {
    return LiveVision(
        backdropPath = this.backdrop_path,
        firstAirDate = this.first_air_date,
        genreIds = this.genre_ids,
        id = this.id,
        name = this.name,
        originCountry = this.origin_country,
        originalLanguage = this.original_language,
        originalName = this.original_name,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.poster_path,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
    )
}

fun DetailsTelevisionMeta.castToDetailsTelevision(): DetailsTelevision {
    return DetailsTelevision(
        adult = this.adult,
        backdropPath = this.backdrop_path,
        createdBy = created_by?.castToListCreatedEntity(),
        episodeRunTime = this.episode_run_time,
        firstAirDate = this.first_air_date,
        genres = this.genres?.castToListGenreEntity(),
        homepage = this.homepage,
        id = this.id,
        inProduction = this.in_production,
        languages = this.languages,
        lastAirDate = this.last_air_date,
        lastEpisodeToAir = this.last_episode_to_air?.castToLastEpisodeEntity(),
        name = this.name,
        networks = this.networks?.castToListNetworkEntity(),
        nextEpisodeToAir = this.next_episode_to_air?.castToNextEpisodeEntity(),
        numberOfEpisodes = this.number_of_episodes,
        numberOfSeasons = this.number_of_seasons,
        originCountry = this.origin_country,
        originalLanguage = this.original_language,
        originalName = this.original_name,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.poster_path,
        productionCompanies = this.production_companies?.castToListProductionCompanyEntity(),
        productionCountries = this.production_countries?.castToListProductionCountryEntity(),
        seasons = this.seasons?.castToListSeasonEntity(),
        spokenLanguages = this.spoken_languages?.castToListLanguageEntity(),
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
    )
}

fun NextEpisodeToAir.castToNextEpisodeEntity(): NextEpisodeToAirEntity {
    return NextEpisodeToAirEntity(
        id = this.id,
        name = this.name,
        overview = this.overview,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
        airDate = this.air_date,
        episodeNumber = this.episode_number,
        productionCode = this.production_code,
        runtime = this.runtime,
        seasonNumber = this.season_number,
        showId = this.show_id,
        stillPath = this.still_path,
    )
}
fun DetailsTvDB.castToDetailsTelevision(): DetailsTelevision {
    return DetailsTelevision(
        adult = this.adult,
        backdropPath = this.backdropPath,
        createdBy = this.createdBy?.castToList(),
        episodeRunTime = emptyList(),
        firstAirDate = this.firstAirDate,
        genres = this.genres?.castToList(),
        homepage = this.homepage,
        id = this.id,
        inProduction = this.inProduction,
        languages = this.languages?.castToList(),
        lastAirDate = this.lastAirDate,
        lastEpisodeToAir = this.lastEpisodeToAir?.castToClass(),
        name = this.name,
        networks = this.networks?.castToList(),
        nextEpisodeToAir = this.nextEpisodeToAir?.castToClass(),
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons,
        originCountry = this.originCountry?.castToList(),
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies?.castToList(),
        productionCountries = this.productionCountries.castToList(),
        seasons = this.seasons?.castToList(),
        spokenLanguages = this.spokenLanguages?.castToList(),
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}

fun CreatedBy.castToCreatedEntity(): CreatedEntity {
    return CreatedEntity(
        creditId = this.credit_id,
        gender = this.gender,
        id = this.id,
        name = this.name,
        profilePath = this.profile_path,
    )
}

fun ProductionCompany.castToProductionCompanyEntity(): ProductionCompanyEntity {
    return ProductionCompanyEntity(
        id = this.id,
        logoPath = this.logo_path,
        name = this.name,
        originCountry = this.origin_country,
    )
}

fun ProductionCountry.castToProductionCountryEntity(): ProductionCountryEntity {
    return ProductionCountryEntity(
        iso31661 = this.iso_3166_1,
        name = this.name,
    )
}

fun Network.castToNetworkEntity(): NetworkEntity {
    return NetworkEntity(
        id = this.id,
        logoPath = this.logo_path,
        name = this.name,
        originCountry = this.origin_country,
    )
}
fun LastEpisodeToAir.castToLastEpisodeEntity(): LastEpisodeToAirEntity {
    return LastEpisodeToAirEntity(
        airDate = this.air_date,
        episodeNumber = this.episode_number,
        id = this.id,
        name = this.name,
        overview = this.overview,
        productionCode = this.production_code,
        runtime = this.runtime,
        seasonNumber = this.season_number,
        showId = this.show_id,
        stillPath = this.still_path,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
    )
}

fun Genre.castToGenreEntity(): GenreEntity {
    return GenreEntity(
        id = this.id,
        name = this.name,
    )
}

fun Season.castToSeasonEntity(): SeasonEntity {
    return SeasonEntity(
        airDate = this.air_date,
        episodeCount = this.episode_count,
        id = this.id,
        name = this.name,
        overview = this.overview,
        poster_path = this.poster_path,
        season_number = this.season_number,
    )
}

fun SpokenLanguage.castToLanguageEntity(): LanguageEntity {
    return LanguageEntity(
        englishName = this.english_name,
        iso6391 = this.iso_639_1,
        name = this.name,
    )
}

private fun List<SpokenLanguage>.castToListLanguageEntity(): List<LanguageEntity> {
    return this.map {
        it.castToLanguageEntity()
    }
}

private fun List<Season>.castToListSeasonEntity(): List<SeasonEntity> {
    return this.map {
        it.castToSeasonEntity()
    }
}

private fun List<ProductionCompany>.castToListProductionCompanyEntity(): List<ProductionCompanyEntity> {
    return this.map {
        it.castToProductionCompanyEntity()
    }
}

private fun List<ProductionCountry>.castToListProductionCountryEntity(): List<ProductionCountryEntity> {
    return this.map {
        it.castToProductionCountryEntity()
    }
}
private fun List<CreatedBy>.castToListCreatedEntity(): List<CreatedEntity> {
    return this.map { created ->
        created.castToCreatedEntity()
    }
}

private fun List<Network>.castToListNetworkEntity(): List<NetworkEntity> {
    return this.map { network ->
        network.castToNetworkEntity()
    }
}

private fun List<Genre>.castToListGenreEntity(): List<GenreEntity> {
    return this.map { genre ->
        genre.castToGenreEntity()
    }
}

fun List<Television>.castToListVision(): List<LiveVision> {
    return this.map { vision ->
        vision.castToLiveVision()
    }
}

/** flow mapper **/
fun Flow<List<RatedTvDB>>.toTheaterFlowLists(): Flow<List<LiveVision>> {
    return this.map { list ->
        list.map { db ->
            val genres = if (db.genreIds?.isNotEmpty() == true) db.genreIds.castToList<Int>() else emptyList()
            val orgCountry = if (db.originCountry?.isNotEmpty() == true) db.originCountry.castToList<String>() else emptyList()
            LiveVision(
                backdropPath = db.backdropPath,
                firstAirDate = db.firstAirDate,
                genreIds = genres,
                id = db.id,
                name = db.name,
                originCountry = orgCountry,
                originalLanguage = db.originalLanguage,
                originalName = db.originalName,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

fun Flow<List<SearchTvDB>>.toTvSearchFlowLists(): Flow<List<LiveVision>> {
    return this.map { list ->
        list.map { db ->
            val genres = if (db.genreIds?.isNotEmpty() == true) db.genreIds.castToList<Int>() else emptyList()
            val orgCountry = if (db.originCountry?.isNotEmpty() == true) db.originCountry.castToList<String>() else emptyList()
            LiveVision(
                backdropPath = db.backdropPath,
                firstAirDate = db.firstAirDate,
                genreIds = genres,
                id = db.id,
                name = db.name,
                originCountry = orgCountry,
                originalLanguage = db.originalLanguage,
                originalName = db.originalName,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

fun Flow<List<TodayAirDB>>.toTodayAirFlowLists(): Flow<List<LiveVision>> {
    return this.map { list ->
        list.map { db ->
            val genres = if (db.genreIds?.isNotEmpty() == true) db.genreIds.castToList<Int>() else emptyList()
            val orgCountry = if (db.originCountry?.isNotEmpty() == true) db.originCountry.castToList<String>() else emptyList()
            LiveVision(
                backdropPath = db.backdropPath,
                firstAirDate = db.firstAirDate,
                genreIds = genres,
                id = db.id,
                name = db.name,
                originCountry = orgCountry,
                originalLanguage = db.originalLanguage,
                originalName = db.originalName,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

fun Flow<List<DiscoverDB>>.toDiscoverFlowLists(): Flow<List<LiveVision>> {
    return this.map { list ->
        list.map { db ->
            val genres = if (db.genreIds?.isNotEmpty() == true) db.genreIds.castToList<Int>() else emptyList()
            val orgCountry = if (db.originCountry?.isNotEmpty() == true) db.originCountry.castToList<String>() else emptyList()
            LiveVision(
                backdropPath = db.backdropPath,
                firstAirDate = db.firstAirDate,
                genreIds = genres,
                id = db.id,
                name = db.name,
                originCountry = orgCountry,
                originalLanguage = db.originalLanguage,
                originalName = db.originalName,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}

fun Flow<List<TrendingDB>>.toTrendingFlowLists(): Flow<List<LiveVision>> {
    return this.map { list ->
        list.map { db ->
            val genres = if (db.genreIds?.isNotEmpty() == true) db.genreIds.castToList<Int>() else emptyList()
            val orgCountry = if (db.originCountry?.isNotEmpty() == true) db.originCountry.castToList<String>() else emptyList()
            LiveVision(
                backdropPath = db.backdropPath,
                firstAirDate = db.firstAirDate,
                genreIds = genres,
                id = db.id,
                name = db.name,
                originCountry = orgCountry,
                originalLanguage = db.originalLanguage,
                originalName = db.originalName,
                overview = db.overview,
                popularity = db.popularity,
                posterPath = db.posterPath,
                voteAverage = db.voteAverage,
                voteCount = db.voteCount,
            )
        }
    }
}
