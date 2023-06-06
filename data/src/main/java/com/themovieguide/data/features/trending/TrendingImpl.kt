package com.themovieguide.data.features.trending

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.castToLiveVision
import com.themovieguide.data.sources.local.mapper.castTrendingDB
import com.themovieguide.data.sources.local.repository.trending.TrendingDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.trending.TrendingTv
import com.themovieguide.domain.states.television.StateTvMeta
import javax.inject.Inject

class TrendingImpl @Inject constructor(
    private val api: ApiServices,
    private val storage: TrendingDBRepository,
    private val signal: Connectivity,
) : TrendingTv {

    private val configKey = BuildConfig.API_KEY

    override suspend fun fetchTrending(): StateTvMeta {
        return if (signal.isInternetConnected()) {
            val response = api.getTrendingTelevision(apiKey = configKey)
            val mapData = response.results?.toList() ?: emptyList()
            val tvList = mapData.map { tv ->
                tv.castToLiveVision()
            }
            tvList.forEach { vision ->
                val db = vision.castTrendingDB()
                storage.insertTelevision(db)
            }
            StateTvMeta.OnSuccess(data = SUCCESS)
        } else {
            StateTvMeta.NoInternet
        }
    }
}
