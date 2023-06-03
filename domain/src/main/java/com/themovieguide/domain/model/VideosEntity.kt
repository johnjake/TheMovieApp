package com.themovieguide.domain.model

data class VideosEntity(
    val results: List<ResultEntity>? = emptyList()
) {
    constructor() : this(results = emptyList())
}
