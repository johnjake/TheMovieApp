package com.themovieguide.data.sources.local.repository.details

import com.themovieguide.data.sources.local.model.DetailsTvDB
import kotlinx.coroutines.flow.Flow

interface DetailsDBRepository {
    fun insert(tv: DetailsTvDB)
    fun insertTelevision(tv: DetailsTvDB)
    fun getTelevision(): Flow<List<DetailsTvDB>>
    fun getTelevisionByTitle(): Flow<List<DetailsTvDB>>
    suspend fun getTelevisionById(tvId: Int): DetailsTvDB
    suspend fun deleteTelevision(tvId: Int)
    suspend fun delete()
}
