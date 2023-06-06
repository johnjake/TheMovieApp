package com.themovieguide.domain.features.trending

import com.themovieguide.domain.states.television.StateTelevision

interface TrendingTvRepository {
    suspend fun fetchTrendingShow(): StateTelevision
}
