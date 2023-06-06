package com.themovieguide.domain.model

import com.themovieguide.domain.utils.EMPTY

data class ProductionCountryEntity(
    val iso31661: String? = EMPTY,
    val name: String? = EMPTY
) {
    constructor() : this(
        iso31661 = EMPTY,
        name = EMPTY
    )
}
