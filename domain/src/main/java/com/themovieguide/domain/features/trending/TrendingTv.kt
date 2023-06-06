package com.themovieguide.domain.features.trending

import com.themovieguide.domain.states.television.StateTvMeta

interface TrendingTv {
    suspend fun fetchTrending(): StateTvMeta
}
