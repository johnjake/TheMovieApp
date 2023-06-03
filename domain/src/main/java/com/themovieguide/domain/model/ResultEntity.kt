package com.themovieguide.domain.model

import com.themovieguide.domain.utils.EMPTY

data class ResultEntity(
    val id: String? = EMPTY,
    val iso_3166_1: String? = EMPTY,
    val iso_639_1: String? = EMPTY,
    val key: String? = EMPTY,
    val name: String? = EMPTY,
    val official: Boolean? = false,
    val published_at: String? = EMPTY,
    val site: String? = EMPTY,
    val size: Int? = 0,
    val type: String? = EMPTY
)
