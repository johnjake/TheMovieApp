package com.themovieguide.data.features.searchtv

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.castToLiveVision
import com.themovieguide.data.sources.local.mapper.castToTelevisionSearchDB
import com.themovieguide.data.sources.local.repository.searchtv.SearchTvStorageRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.searchtv.SearchTelevision
import com.themovieguide.domain.states.television.StateTvMeta
import javax.inject.Inject

class SearchTelevisionImpl @Inject constructor(
    private val api: ApiServices,
    private val storage: SearchTvStorageRepository,
    private val signal: Connectivity,
) : SearchTelevision {
    private val configKey = BuildConfig.API_KEY
    override suspend fun searchTelevision(query: String): StateTvMeta {
        return if (signal.isInternetConnected()) {
            val response = api.getSearchTelevision(apiKey = configKey, query = query)
            val mapData = response.results?.toList() ?: emptyList()
            val tvList = mapData.map { tv ->
                tv.castToLiveVision()
            }
            tvList.forEach { vision ->
                val db = vision.castToTelevisionSearchDB()
                storage.insertTelevision(db)
            }
            StateTvMeta.OnSuccess(data = SUCCESS)
        } else {
            StateTvMeta.NoInternet
        }
    }
}
