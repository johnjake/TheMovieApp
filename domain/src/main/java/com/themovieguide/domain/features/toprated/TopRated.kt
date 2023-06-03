package com.themovieguide.domain.features.toprated

import com.themovieguide.domain.states.showing.StateMeta

interface TopRated {
    suspend fun topRated(page: Int): StateMeta
}
