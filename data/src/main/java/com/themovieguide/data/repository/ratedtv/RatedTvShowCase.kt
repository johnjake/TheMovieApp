package com.themovieguide.data.repository.ratedtv

import com.themovieguide.data.mapper.toTheaterFlowLists
import com.themovieguide.data.sources.local.repository.ratedtv.RatedTelevisionDBRepository
import com.themovieguide.domain.features.ratedtv.RatedTv
import com.themovieguide.domain.features.ratedtv.RatedTvRepository
import com.themovieguide.domain.states.television.StateTelevision
import com.themovieguide.domain.states.television.StateTvMeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RatedTvShowCase @Inject constructor(
    private val television: RatedTv,
    private val storage: RatedTelevisionDBRepository
) : RatedTvRepository {
    override suspend fun fetchTopRatedShow(): StateTelevision {
        StateTelevision.ShowLoader
        return withContext(Dispatchers.IO) {
            when (val response = television.fetchRatedTv()) {
                is StateTvMeta.OnSuccess -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision()
                    StateTelevision.OnSuccess(data = tvStorage.toTheaterFlowLists())
                }
                is StateTvMeta.OnFailed -> {
                    StateTelevision.HideLoader
                    StateTelevision.OnFailed(error = response.error)
                }
                is StateTvMeta.NoInternet -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision()
                    StateTelevision.OnSuccess(data = tvStorage.toTheaterFlowLists())
                }
            }
        }
    }
}