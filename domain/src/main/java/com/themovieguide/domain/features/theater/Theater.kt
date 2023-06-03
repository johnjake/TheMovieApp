package com.themovieguide.domain.features.theater

import com.themovieguide.domain.states.showing.StateMeta

interface Theater {
    suspend fun inTheater(page: Int): StateMeta
}
