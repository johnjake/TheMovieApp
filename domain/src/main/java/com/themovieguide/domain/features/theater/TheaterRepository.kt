package com.themovieguide.domain.features.theater

import com.themovieguide.domain.states.showing.StateShowing

interface TheaterRepository {
    suspend fun fetchInTheater(page: Int): StateShowing
}
