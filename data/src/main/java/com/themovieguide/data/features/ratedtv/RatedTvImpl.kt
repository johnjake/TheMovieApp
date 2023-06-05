package com.themovieguide.data.features.ratedtv

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.castToLiveVision
import com.themovieguide.data.sources.local.mapper.castToTelevisionDB
import com.themovieguide.data.sources.local.repository.ratedtv.RatedTelevisionDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.ratedtv.RatedTv
import com.themovieguide.domain.states.television.StateTvMeta
import javax.inject.Inject

class RatedTvImpl @Inject constructor(
    private val api: ApiServices,
    private val storage: RatedTelevisionDBRepository,
    private val signal: Connectivity,
) : RatedTv {

    private val configKey = BuildConfig.API_KEY

    override suspend fun fetchRatedTv(): StateTvMeta {
        return if (signal.isInternetConnected()) {
            val response = api.getTopRatedTvSeries(apiKey = configKey)
            val mapData = response.results?.toList() ?: emptyList()
            val tvList = mapData.map { tv ->
                tv.castToLiveVision()
            }
            tvList.forEach { vision ->
                val db = vision.castToTelevisionDB()
                storage.insertTelevision(db)
            }
            StateTvMeta.OnSuccess(data = SUCCESS)
        } else {
            StateTvMeta.NoInternet
        }
    }
}
