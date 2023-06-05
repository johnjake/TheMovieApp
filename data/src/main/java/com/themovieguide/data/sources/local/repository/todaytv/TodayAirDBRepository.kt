package com.themovieguide.data.sources.local.repository.todaytv

import com.themovieguide.data.sources.local.model.TodayAirDB
import kotlinx.coroutines.flow.Flow

interface TodayAirDBRepository {
    fun insert(tv: TodayAirDB)
    fun insertTelevision(tv: TodayAirDB)
    fun getTelevision(): Flow<List<TodayAirDB>>
    fun getTelevisionByTitle(): Flow<List<TodayAirDB>>
    suspend fun getTelevisionById(tvId: Int): TodayAirDB
    suspend fun deleteTelevision(tvId: Int)
    suspend fun delete()
}
