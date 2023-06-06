package com.themovieguide.data.repository.discovertv

import com.themovieguide.data.mapper.toDiscoverFlowLists
import com.themovieguide.data.sources.local.repository.discover.DiscoverDBRepository
import com.themovieguide.domain.features.discover.DiscoverTv
import com.themovieguide.domain.features.discover.DiscoverTvRepository
import com.themovieguide.domain.states.television.StateTelevision
import com.themovieguide.domain.states.television.StateTvMeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DiscoverTvShowCase @Inject constructor(
    private val television: DiscoverTv,
    private val storage: DiscoverDBRepository,
) : DiscoverTvRepository {
    override suspend fun fetchDiscoverShow(): StateTelevision {
        StateTelevision.ShowLoader
        return withContext(Dispatchers.IO) {
            when (val response = television.fetchDiscovery()) {
                is StateTvMeta.OnSuccess -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision()
                    StateTelevision.OnSuccess(data = tvStorage.toDiscoverFlowLists())
                }
                is StateTvMeta.OnFailed -> {
                    StateTelevision.HideLoader
                    StateTelevision.OnFailed(error = response.error)
                }
                is StateTvMeta.NoInternet -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision()
                    StateTelevision.OnSuccess(data = tvStorage.toDiscoverFlowLists())
                }
            }
        }
    }
}
