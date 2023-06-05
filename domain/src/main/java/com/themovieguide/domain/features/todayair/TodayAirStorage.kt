package com.themovieguide.domain.features.todayair

import com.themovieguide.domain.states.television.StateTvMeta

interface TodayAirStorage {
    suspend fun fetchTodayAir(): StateTvMeta
}
