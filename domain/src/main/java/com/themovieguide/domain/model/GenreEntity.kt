package com.themovieguide.domain.model

import com.themovieguide.domain.utils.EMPTY

data class GenreEntity(
    val id: Int? = 0,
    val name: String? = EMPTY,
) {
    constructor() : this(
        id = 0,
        name = EMPTY
    )
}
