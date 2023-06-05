package com.themovieguide.domain.features.todayair

import com.themovieguide.domain.states.television.StateTelevision

interface TodayAirStorageRepository {
    suspend fun fetchTodayAirShow(): StateTelevision
}
