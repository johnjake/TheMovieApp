package com.themovieguide.data.repository.trending

import com.themovieguide.data.mapper.toTrendingFlowLists
import com.themovieguide.data.sources.local.repository.trending.TrendingDBRepository
import com.themovieguide.domain.features.trending.TrendingTv
import com.themovieguide.domain.features.trending.TrendingTvRepository
import com.themovieguide.domain.states.television.StateTelevision
import com.themovieguide.domain.states.television.StateTvMeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingTvShowCase @Inject constructor(
    private val television: TrendingTv,
    private val storage: TrendingDBRepository,
) : TrendingTvRepository {
    override suspend fun fetchTrendingShow(): StateTelevision {
        StateTelevision.ShowLoader
        return withContext(Dispatchers.IO) {
            when (val response = television.fetchTrending()) {
                is StateTvMeta.OnSuccess -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision()
                    StateTelevision.OnSuccess(data = tvStorage.toTrendingFlowLists())
                }
                is StateTvMeta.OnFailed -> {
                    StateTelevision.HideLoader
                    StateTelevision.OnFailed(error = response.error)
                }
                is StateTvMeta.NoInternet -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision()
                    StateTelevision.OnSuccess(data = tvStorage.toTrendingFlowLists())
                }
            }
        }
    }
}
