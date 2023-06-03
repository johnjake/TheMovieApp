package com.themovieguide.domain.features.search

import com.themovieguide.domain.states.showing.StateMeta

interface Search {
    suspend fun searchMovie(query: String, page: Int): StateMeta
}
