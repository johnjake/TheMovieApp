package com.themovieguide.data.features.discover

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.castToLiveVision
import com.themovieguide.data.sources.local.mapper.castDiscoverDB
import com.themovieguide.data.sources.local.repository.discover.DiscoverDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.discover.DiscoverTv
import com.themovieguide.domain.states.television.StateTvMeta
import javax.inject.Inject

class DiscoverTvImpl @Inject constructor(
    private val api: ApiServices,
    private val storage: DiscoverDBRepository,
    private val signal: Connectivity,
) : DiscoverTv {

    private val configKey = BuildConfig.API_KEY

    override suspend fun fetchDiscovery(): StateTvMeta {
        return if (signal.isInternetConnected()) {
            val response = api.getDiscoverTelevision(apiKey = configKey)
            val mapData = response.results?.toList() ?: emptyList()
            val tvList = mapData.map { tv ->
                tv.castToLiveVision()
            }
            tvList.forEach { vision ->
                val db = vision.castDiscoverDB()
                storage.insertTelevision(db)
            }
            StateTvMeta.OnSuccess(data = SUCCESS)
        } else {
            StateTvMeta.NoInternet
        }
    }
}
