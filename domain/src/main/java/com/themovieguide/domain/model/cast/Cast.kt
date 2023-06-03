package com.themovieguide.domain.model.cast

import com.themovieguide.domain.utils.EMPTY

data class Cast(
    val adult: Boolean? = false,
    val castId: Int? = 0,
    val character: String? = EMPTY,
    val creditId: String? = EMPTY,
    val gender: Int? = 0,
    val id: Int? = 0,
    val knownForDepartment: String? = EMPTY,
    val name: String? = EMPTY,
    val order: Int? = 0,
    val originalName: String? = EMPTY,
    val popularity: Double? = 0.0,
    val profilePath: String? = EMPTY,
) {
    constructor() : this(
        adult = false,
        castId = 0,
        character = EMPTY,
        creditId = EMPTY,
        gender = 0,
        id = 0,
        knownForDepartment = EMPTY,
        name = EMPTY,
        order = 0,
        originalName = EMPTY,
        popularity = 0.0,
        profilePath = EMPTY,
    )
}
