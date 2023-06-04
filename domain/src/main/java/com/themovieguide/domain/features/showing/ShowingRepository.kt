package com.themovieguide.domain.features.showing

import com.themovieguide.domain.states.showing.StateShowing

interface ShowingRepository {
    suspend fun nowShowing(): StateShowing
}
