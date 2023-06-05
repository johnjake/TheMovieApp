package com.themovieguide.data.repository.searchtv

import com.themovieguide.data.mapper.toTvSearchFlowLists
import com.themovieguide.data.sources.local.repository.searchtv.SearchTvStorageRepository
import com.themovieguide.domain.features.searchtv.SearchTelevision
import com.themovieguide.domain.features.searchtv.SearchTelevisionRepository
import com.themovieguide.domain.states.television.StateTelevision
import com.themovieguide.domain.states.television.StateTvMeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchTelevisionCase @Inject constructor(
    private val repository: SearchTelevision,
    private val storage: SearchTvStorageRepository,
) : SearchTelevisionRepository {
    override suspend fun fetchSearch(query: String): StateTelevision {
        StateTelevision.ShowLoader
        return withContext(Dispatchers.IO) {
            when (val response = repository.searchTelevision(query = query)) {
                is StateTvMeta.OnSuccess -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision(query = query)
                    StateTelevision.OnSuccess(data = tvStorage.toTvSearchFlowLists())
                }
                is StateTvMeta.OnFailed -> {
                    StateTelevision.HideLoader
                    StateTelevision.OnFailed(error = response.error)
                }
                is StateTvMeta.NoInternet -> {
                    StateTelevision.HideLoader
                    val tvStorage = storage.getTelevision(query = query)
                    StateTelevision.OnSuccess(data = tvStorage.toTvSearchFlowLists())
                }
            }
        }
    }
}
