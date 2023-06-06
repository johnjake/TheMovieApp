package com.themovieguide.domain.model.television

import com.themovieguide.domain.utils.EMPTY

data class LastEpisodeToAirEntity(
    val airDate: String? = EMPTY,
    val episodeNumber: Int? = 0,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val overview: String? = EMPTY,
    val productionCode: String? = EMPTY,
    val runtime: Int? = 0,
    val seasonNumber: Int? = 0,
    val showId: Int? = 0,
    val stillPath: String? = EMPTY,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
) {
    constructor() : this(
        airDate = EMPTY,
        episodeNumber = 0,
        id = 0,
        name = EMPTY,
        overview = EMPTY,
        productionCode = EMPTY,
        runtime = 0,
        seasonNumber = 0,
        showId = 0,
        stillPath = EMPTY,
        voteAverage = 0.0,
        voteCount = 0,
    )
}