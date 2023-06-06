package com.themovieguide.data.repository.details

import com.themovieguide.data.mapper.castToDetailsTelevision
import com.themovieguide.data.sources.local.repository.details.DetailsDBRepository
import com.themovieguide.domain.features.details.GetDetailsTelevision
import com.themovieguide.domain.features.details.GetDetailsTelevisionRepository
import com.themovieguide.domain.states.television.StateSingleTelevision
import com.themovieguide.domain.states.television.StateTvMeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesDetailsCase @Inject constructor(
    private val television: GetDetailsTelevision,
    private val storage: DetailsDBRepository,
) : GetDetailsTelevisionRepository {
    override suspend fun fetchDetailsTelevisionShow(seriesId: Int): StateSingleTelevision {
        StateSingleTelevision.ShowLoader
        return withContext(Dispatchers.IO) {
            when (val response = television.fetchDetailsTv(seriesId = seriesId)) {
                is StateTvMeta.OnSuccess -> {
                    StateSingleTelevision.HideLoader
                    val tvStorage = storage.getTelevisionById(seriesId)
                    StateSingleTelevision.OnSuccess(data = tvStorage.castToDetailsTelevision())
                }
                is StateTvMeta.OnFailed -> {
                    StateSingleTelevision.HideLoader
                    StateSingleTelevision.OnFailed(error = response.error)
                }
                is StateTvMeta.NoInternet -> {
                    StateSingleTelevision.HideLoader
                    val tvStorage = storage.getTelevisionById(seriesId)
                    StateSingleTelevision.OnSuccess(data = tvStorage.castToDetailsTelevision())
                }
            }
        }
    }
}
