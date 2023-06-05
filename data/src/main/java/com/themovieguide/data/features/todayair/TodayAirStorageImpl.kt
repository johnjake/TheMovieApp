package com.themovieguide.data.features.todayair

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.castToLiveVision
import com.themovieguide.data.sources.local.mapper.castToTodayAirDB
import com.themovieguide.data.sources.local.repository.todaytv.TodayAirDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.todayair.TodayAirStorage
import com.themovieguide.domain.states.television.StateTvMeta
import javax.inject.Inject

class TodayAirStorageImpl @Inject constructor(
    private val api: ApiServices,
    private val storage: TodayAirDBRepository,
    private val signal: Connectivity,
) : TodayAirStorage {

    private val configKey = BuildConfig.API_KEY

    override suspend fun fetchTodayAir(): StateTvMeta {
        return if (signal.isInternetConnected()) {
            val response = api.getTodayTelevision(apiKey = configKey)
            val mapData = response.results?.toList() ?: emptyList()
            val tvList = mapData.map { tv ->
                tv.castToLiveVision()
            }
            tvList.forEach { vision ->
                val db = vision.castToTodayAirDB()
                storage.insertTelevision(db)
            }
            StateTvMeta.OnSuccess(data = SUCCESS)
        } else {
            StateTvMeta.NoInternet
        }
    }
}
