package com.themovieguide.data.sources.local.repository.ratedtv

import com.themovieguide.data.sources.local.model.RatedTvDB
import kotlinx.coroutines.flow.Flow

interface RatedTelevisionDBRepository {
    fun insert(tv: RatedTvDB)
    fun insertTelevision(tv: RatedTvDB)
    fun getTelevision(): Flow<List<RatedTvDB>>
    fun getTelevisionByTitle(): Flow<List<RatedTvDB>>
    suspend fun getTelevisionById(tvId: Int): RatedTvDB
    suspend fun deleteTelevision(tvId: Int)
    suspend fun delete()
}
