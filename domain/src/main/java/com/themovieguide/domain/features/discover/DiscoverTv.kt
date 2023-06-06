package com.themovieguide.domain.features.discover

import com.themovieguide.domain.states.television.StateTvMeta

interface DiscoverTv {
    suspend fun fetchDiscovery(): StateTvMeta
}
