package com.themovieguide.data.repository.todayair

import com.themovieguide.data.mapper.toTodayAirFlowLists
import com.themovieguide.data.sources.local.repository.todaytv.TodayAirDBRepository
import com.themovieguide.domain.features.todayair.TodayAirStorage
import com.themovieguide.domain.features.todayair.TodayAirStorageRepository
import com.themovieguide.domain.states.television.StateTelevision
import com.themovieguide.domain.states.television.StateTvMeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodayAirStorageCase @Inject constructor(
    private val repository: TodayAirStorage,
    private val storage: TodayAirDBRepository,
) : TodayAirStorageRepository {
    override suspend fun fetchTodayAirShow(): StateTelevision {
        StateTelevision.ShowLoader
        return withContext(Dispatchers.IO) {
            when (val response = repository.fetchTodayAir()) {
                is StateTvMeta.OnSuccess -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision()
                    StateTelevision.OnSuccess(data = tvStorage.toTodayAirFlowLists())
                }
                is StateTvMeta.OnFailed -> {
                    StateTelevision.HideLoader
                    StateTelevision.OnFailed(error = response.error)
                }
                is StateTvMeta.NoInternet -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision()
                    StateTelevision.OnSuccess(data = tvStorage.toTodayAirFlowLists())
                }
            }
        }
    }
}
