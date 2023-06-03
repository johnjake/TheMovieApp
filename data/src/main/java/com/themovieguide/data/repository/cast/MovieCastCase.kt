package com.themovieguide.data.repository.cast

import com.themovieguide.domain.features.cast.CastRepository
import com.themovieguide.domain.features.cast.MovieCast
import com.themovieguide.domain.states.cast.StateCast
import com.themovieguide.domain.states.cast.StateCastMeta
import com.themovieguide.domain.utils.EMPTY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieCastCase @Inject constructor(private val repository: MovieCast) : CastRepository {
    override suspend fun fetchCast(movieId: Int): StateCast {
        return withContext(Dispatchers.IO) {
            when (val response = repository.castInMovie(movieId)) {
                is StateCastMeta.OnSuccess -> {
                    StateCast.HideLoader
                    StateCast.OnSuccess(data = response.data)
                }
                is StateCastMeta.OnFailed -> {
                    delay(1000)
                    StateCast.HideLoader
                    StateCast.OnFailed(error = response.error ?: EMPTY)
                }
                else -> {
                    StateCast.HideLoader
                    StateCast.OnFailed(error = "Cast: No response")
                }
            }
        }
    }
}
