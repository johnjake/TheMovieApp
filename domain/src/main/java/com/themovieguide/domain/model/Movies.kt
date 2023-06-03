package com.themovieguide.domain.model

import com.themovieguide.domain.utils.EMPTY

data class Movies(
    var key: Int = 0,
    val adult: Boolean? = false,
    val backdropPath: String? = EMPTY,
    val genreIds: List<Int>? = emptyList(),
    val id: Int? = 0,
    val originalLanguage: String? = EMPTY,
    val originalTitle: String? = EMPTY,
    val overview: String? = EMPTY,
    val popularity: Double? = 0.0,
    val posterPath: String? = EMPTY,
    val releaseDate: String? = EMPTY,
    val title: String? = EMPTY,
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
) {
    constructor() : this(
        key = 0,
        adult = false,
        backdropPath = EMPTY,
        genreIds = emptyList(),
        id = 0,
        originalLanguage = EMPTY,
        originalTitle = EMPTY,
        overview = EMPTY,
        popularity = 0.0,
        posterPath = EMPTY,
        releaseDate = EMPTY,
        title = EMPTY,
        video = false,
        voteAverage = 0.0,
        voteCount = 0,
    )
}
