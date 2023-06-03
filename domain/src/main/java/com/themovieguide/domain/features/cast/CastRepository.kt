package com.themovieguide.domain.features.cast

import com.themovieguide.domain.states.cast.StateCast

interface CastRepository {
    suspend fun fetchCast(movieId: Int): StateCast
}
