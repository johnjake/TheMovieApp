package com.themovieguide.domain.model

import com.themovieguide.domain.utils.EMPTY

data class BelongsCollectionEntity(
    val backdrop_path: String? = EMPTY,
    val id: Int? = 0,
    val name: String? = EMPTY,
    val poster_path: String? = EMPTY,
) {
    constructor() : this(backdrop_path = EMPTY, id = 0, name = EMPTY, poster_path = EMPTY)
}
