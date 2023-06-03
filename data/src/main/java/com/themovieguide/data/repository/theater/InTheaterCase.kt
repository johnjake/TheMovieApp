package com.themovieguide.data.repository.theater

import com.themovieguide.domain.features.theater.Theater
import com.themovieguide.domain.features.theater.TheaterRepository
import com.themovieguide.domain.states.showing.StateMeta
import com.themovieguide.domain.states.showing.StateShowing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InTheaterCase @Inject constructor(private val repository: Theater) : TheaterRepository {
    override suspend fun fetchInTheater(page: Int): StateShowing {
        return withContext(Dispatchers.IO) {
            when (val response = repository.inTheater(page = page)) {
                is StateMeta.OnSuccess -> {
                    StateShowing.HideLoader
                    StateShowing.OnSuccess(data = response.data)
                }
                is StateMeta.OnFailed -> {
                    StateShowing.HideLoader
                    StateShowing.OnFailed(error = response.error)
                }
                else -> {
                    StateShowing.HideLoader
                    StateShowing.OnFailed(error = "No response")
                }
            }
        }
    }
}
