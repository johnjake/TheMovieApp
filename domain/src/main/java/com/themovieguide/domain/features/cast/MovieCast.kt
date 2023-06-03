package com.themovieguide.domain.features.cast

import com.themovieguide.domain.states.cast.StateCastMeta

interface MovieCast {
    suspend fun castInMovie(movieId: Int): StateCastMeta
}
