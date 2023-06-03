package com.themovieguide.domain.features.upcoming

import com.themovieguide.domain.states.showing.StateShowing

interface UpcomingRepository {
    suspend fun fetchUpcoming(page: Int): StateShowing
}
