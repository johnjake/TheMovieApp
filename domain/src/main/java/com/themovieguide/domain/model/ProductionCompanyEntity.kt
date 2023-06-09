package com.themovieguide.domain.model

import com.themovieguide.domain.utils.EMPTY

data class ProductionCompanyEntity(
    val id: Int? = 0,
    val logoPath: String? = EMPTY,
    val name: String? = EMPTY,
    val originCountry: String? = EMPTY
) {
    constructor() : this(
        id = 0,
        logoPath = EMPTY,
        name = EMPTY,
        originCountry = EMPTY
    )
}
