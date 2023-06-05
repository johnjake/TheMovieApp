package com.themovieguide.data.sources.local.repository.searchtv

import com.themovieguide.data.sources.local.model.SearchTvDB
import kotlinx.coroutines.flow.Flow

interface SearchTvStorageRepository {
    fun insert(tv: SearchTvDB)
    fun insertTelevision(tv: SearchTvDB)
    fun getTelevision(query: String): Flow<List<SearchTvDB>>
    fun getTelevisionByTitle(): Flow<List<SearchTvDB>>
    suspend fun getTelevisionById(tvId: Int): SearchTvDB
    suspend fun deleteTelevision(tvId: Int)
    suspend fun delete()
}
