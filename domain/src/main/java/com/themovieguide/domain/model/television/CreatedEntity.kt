package com.themovieguide.domain.model.television

import com.themovieguide.domain.utils.EMPTY

data class CreatedEntity(
    val creditId: String? = EMPTY,
    val gender: Int? = 0,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val profilePath: String? = EMPTY,
) {
    constructor() : this(
        creditId = EMPTY,
        gender = 0,
        id = 0,
        name = EMPTY,
        profilePath = EMPTY,
    )
}
