package com.themovieguide.data.sources.local.repository.trending

import com.themovieguide.data.sources.local.model.TrendingDB
import kotlinx.coroutines.flow.Flow

interface TrendingDBRepository {
    fun insert(tv: TrendingDB)
    fun insertTelevision(tv: TrendingDB)
    fun getTelevision(): Flow<List<TrendingDB>>
    fun getTelevisionByTitle(): Flow<List<TrendingDB>>
    suspend fun getTelevisionById(tvId: Int): TrendingDB
    suspend fun deleteTelevision(tvId: Int)
    suspend fun delete()
}
