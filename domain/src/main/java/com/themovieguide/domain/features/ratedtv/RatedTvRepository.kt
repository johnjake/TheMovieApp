package com.themovieguide.domain.features.ratedtv

import com.themovieguide.domain.states.television.StateTelevision

interface RatedTvRepository {
    suspend fun fetchTopRatedShow(): StateTelevision
}
