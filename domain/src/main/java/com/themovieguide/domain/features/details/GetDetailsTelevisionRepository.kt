package com.themovieguide.domain.features.details

import com.themovieguide.domain.states.television.StateSingleTelevision

interface GetDetailsTelevisionRepository {
    suspend fun fetchDetailsTelevisionShow(seriesId: Int): StateSingleTelevision
}
