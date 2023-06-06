package com.themovieguide.domain.model

import com.themovieguide.domain.utils.EMPTY

data class LanguageEntity(
    val englishName: String? = EMPTY,
    val iso6391: String? = EMPTY,
    val name: String? = EMPTY,
) {
    constructor() : this(
        englishName = EMPTY,
        iso6391 = EMPTY,
        name = EMPTY
    )
}
