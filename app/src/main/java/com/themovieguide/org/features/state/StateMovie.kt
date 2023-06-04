package com.themovieguide.org.features.state

import com.themovieguide.domain.model.Movies
import kotlinx.coroutines.flow.Flow

sealed class StateMovie<out T> {
    object ShowLoader : StateMovie<Nothing>()
    object HideLoader : StateMovie<Nothing>()
    data class OnSuccess<out T>(val data: List<Movies>) : StateMovie<T>()
    data class OnFailure(val error: String) : StateMovie<Nothing>()
}

sealed class StateFlowMovie<out T> {
    object ShowLoader : StateFlowMovie<Nothing>()
    object HideLoader : StateFlowMovie<Nothing>()
    data class OnSuccess<out T>(val data: Flow<List<Movies>>) : StateFlowMovie<T>()
    data class OnFailure(val error: String) : StateFlowMovie<Nothing>()
}
