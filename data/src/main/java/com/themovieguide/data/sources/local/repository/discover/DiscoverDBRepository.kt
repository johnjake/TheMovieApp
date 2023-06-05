package com.themovieguide.data.sources.local.repository.discover

import com.themovieguide.data.sources.local.model.DiscoverDB
import kotlinx.coroutines.flow.Flow

interface DiscoverDBRepository {
    fun insert(tv: DiscoverDB)
    fun insertTelevision(tv: DiscoverDB)
    fun getTelevision(): Flow<List<DiscoverDB>>
    fun getTelevisionByTitle(): Flow<List<DiscoverDB>>
    suspend fun getTelevisionById(tvId: Int): DiscoverDB
    suspend fun deleteTelevision(tvId: Int)
    suspend fun delete()
}
