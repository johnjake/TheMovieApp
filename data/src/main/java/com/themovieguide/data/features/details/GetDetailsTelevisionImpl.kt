package com.themovieguide.data.features.details

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.sources.local.mapper.castToDetailsDB
import com.themovieguide.data.sources.local.repository.details.DetailsDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.details.GetDetailsTelevision
import com.themovieguide.domain.states.television.StateTvMeta
import javax.inject.Inject

class GetDetailsTelevisionImpl @Inject constructor(
    private val api: ApiServices,
    private val storage: DetailsDBRepository,
    private val signal: Connectivity,
) : GetDetailsTelevision {
    private val configKey = BuildConfig.API_KEY
    override suspend fun fetchDetailsTv(seriesId: Int): StateTvMeta {
        return if (signal.isInternetConnected()) {
            val response = api.getDetailsTelevision(
                seriesId = seriesId,
                apiKey = configKey,
            )
            val dbResponse = response.castToDetailsDB()
            storage.insertTelevision(dbResponse)
            StateTvMeta.OnSuccess(data = SUCCESS)
        } else {
            StateTvMeta.NoInternet
        }
    }
}
