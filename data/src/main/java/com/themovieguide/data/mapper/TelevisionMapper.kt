package com.themovieguide.data.mapper

import com.themovieguide.data.model.dto.television.rated.Television
import com.themovieguide.data.sources.local.model.RatedTvDB
import com.themovieguide.data.sources.local.model.SearchTvDB
import com.themovieguide.data.utils.castToList
import com.themovieguide.domain.model.television.LiveVision
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
