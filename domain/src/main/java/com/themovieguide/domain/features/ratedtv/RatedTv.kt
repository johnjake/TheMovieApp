package com.themovieguide.domain.features.ratedtv

import com.themovieguide.domain.states.television.StateTvMeta

interface RatedTv {
    suspend fun fetchRatedTv(): StateTvMeta
}
