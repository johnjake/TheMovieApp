package com.themovieguide.domain.features.toprated

import com.themovieguide.domain.states.showing.StateShowing

interface TopRatedRepository {
    suspend fun fetchTopRated(page: Int): StateShowing
}
