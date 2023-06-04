package com.themovieguide.data.repository.showing

import com.themovieguide.data.mapper.toTheaterFlowLists
import com.themovieguide.data.sources.local.repository.theater.InTheaterDBRepository
import com.themovieguide.domain.features.showing.Showing
import com.themovieguide.domain.features.showing.ShowingRepository
import com.themovieguide.domain.states.showing.StateMeta
import com.themovieguide.domain.states.showing.StateShowing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowingCase @Inject constructor(
    private val repository: Showing,
    private val db: InTheaterDBRepository,
) : ShowingRepository {
    override suspend fun nowShowing(): StateShowing {
        return withContext(Dispatchers.IO) {
            when (val response = repository.nowShowing()) {
                is StateMeta.OnSuccess -> {
                    StateShowing.HideLoader
                    val moviesDB = db.getMovies()
                    StateShowing.OnSuccess(data = moviesDB.toTheaterFlowLists())
                }
                is StateMeta.OnFailed -> {
                    delay(2000)
                    StateShowing.HideLoader
                    StateShowing.OnFailed(error = response.error)
                }
                is StateMeta.NoInternet -> {
                    StateShowing.HideLoader
                    val moviesDB = db.getMovies()
                    StateShowing.OnSuccess(data = moviesDB.toTheaterFlowLists())
                }
                else -> {
                    StateShowing.HideLoader
                    StateShowing.OnFailed(error = "No response")
                }
            }
        }
    }
}
