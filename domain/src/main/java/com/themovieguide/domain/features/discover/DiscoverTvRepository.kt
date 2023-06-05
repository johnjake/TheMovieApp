package com.themovieguide.domain.features.discover

import com.themovieguide.domain.states.television.StateTelevision

interface DiscoverTvRepository {
    suspend fun fetchDiscoverShow(): StateTelevision
}
