package com.themovieguide.domain.features.showing

import com.themovieguide.domain.states.showing.StateMeta

interface Showing {
    suspend fun nowShowing(): StateMeta
}
