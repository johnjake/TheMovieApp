package com.themovieguide.domain.model.cast

import com.themovieguide.domain.utils.EMPTY

data class Crew(
    val adult: Boolean? = false,
    val creditId: String? = EMPTY,
    val department: String? = EMPTY,
    val gender: Int? = 0,
    val id: Int? = 0,
    val job: String? = EMPTY,
    val knownForDepartment: String? = EMPTY,
    val name: String? = EMPTY,
    val originalName: String? = EMPTY,
    val popularity: Double? = 0.0,
    val profilePath: String? = EMPTY,
)
