package com.themovieguide.domain.features.upcoming

import com.themovieguide.domain.states.showing.StateMeta

interface Upcoming {
    suspend fun upcoming(page: Int): StateMeta
}
