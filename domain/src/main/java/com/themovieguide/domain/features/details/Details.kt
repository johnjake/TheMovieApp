package com.themovieguide.domain.features.details

import com.themovieguide.domain.states.showing.StateSingleMeta

interface Details {
    suspend fun movieDetails(movieId: Int): StateSingleMeta
}
