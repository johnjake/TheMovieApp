package com.themovieguide.domain.model.cast

data class MainCast(
    val cast: List<Cast>? = emptyList(),
    val crew: List<Crew>? = emptyList(),
    val id: Int? = 0,
) {
    constructor() : this(cast = emptyList(), crew = emptyList(), id = 0)
}
