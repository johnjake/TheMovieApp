package com.themovieguide.domain.model.television

import com.themovieguide.domain.utils.EMPTY

data class NextEpisodeToAirEntity(
    val id: Int? = 0,
    val name: String? = EMPTY,
    val overview: String? = EMPTY,
    val voteAverage: Int? = 0,
    val voteCount: Int? = 0,
    val airDate: String? = EMPTY,
    val episodeNumber: Int? = 0,
    val productionCode: String? = EMPTY,
    val runtime: Int? = 0,
    val seasonNumber: Int? = 0,
    val showId: Int? = 0,
    val stillPath: String? = EMPTY
) {
    constructor() : this(
        id = 0,
        name = EMPTY,
        overview = EMPTY,
        voteAverage = 0,
        voteCount = 0,
        airDate = EMPTY,
        episodeNumber = 0,
        productionCode = EMPTY,
        runtime = 0,
        seasonNumber = 0,
        showId = 0,
        stillPath = EMPTY,
    )
}
