package com.themovieguide.data.mapper

import com.themovieguide.data.model.dto.television.rated.Television
import com.themovieguide.domain.model.television.LiveVision

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
