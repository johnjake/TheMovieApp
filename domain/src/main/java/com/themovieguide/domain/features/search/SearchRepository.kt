package com.themovieguide.domain.features.search

import com.themovieguide.domain.states.showing.StateShowing

interface SearchRepository {
    suspend fun fetchSearch(query: String, page: Int): StateShowing
}
