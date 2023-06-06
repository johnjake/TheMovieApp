package com.themovieguide.domain.features.details

import com.themovieguide.domain.states.television.StateTvMeta

interface GetDetailsTelevision {
    suspend fun fetchDetailsTv(seriesId: Int): StateTvMeta
}
