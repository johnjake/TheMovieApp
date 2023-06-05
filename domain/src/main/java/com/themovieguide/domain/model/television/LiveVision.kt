package com.themovieguide.domain.model.television

import com.themovieguide.domain.utils.EMPTY

data class LiveVision(
    val backdropPath: String? = EMPTY,
    val firstAirDate: String? = EMPTY,
    val genreIds: List<Int>? = emptyList(),
    val id: Int? = 0,
    val name: String? = EMPTY,
    val originCountry: List<String>? = emptyList(),
    val originalLanguage: String? = EMPTY,
    val originalName: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val posterPath: String? = EMPTY,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
) {
    constructor() : this(
        backdropPath = EMPTY,
        firstAirDate = EMPTY,
        genreIds = emptyList(),
        id = 0,
        name = EMPTY,
        originCountry = emptyList(),
        originalLanguage = EMPTY,
        originalName = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        posterPath = EMPTY,
        voteAverage = 0.0,
        voteCount = 0,
    )
}
