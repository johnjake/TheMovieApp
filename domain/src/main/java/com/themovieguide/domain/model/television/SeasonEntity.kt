package com.themovieguide.domain.model.television

import com.themovieguide.domain.utils.EMPTY

data class SeasonEntity(
    val airDate: String? = EMPTY,
    val episodeCount: Int? = 0,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val overview: String? = EMPTY,
    val poster_path: String? = EMPTY,
    val season_number: Int? = 0,
) {
    constructor() : this(
        airDate = EMPTY,
        episodeCount = 0,
        id = 0,
        name = EMPTY,
        overview = EMPTY,
        poster_path = EMPTY,
        season_number = 0,
    )
}
