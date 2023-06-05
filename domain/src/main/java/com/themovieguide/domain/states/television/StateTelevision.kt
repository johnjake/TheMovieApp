package com.themovieguide.domain.states.television

import com.themovieguide.domain.model.television.LiveVision
import kotlinx.coroutines.flow.Flow

sealed class StateTelevision {
    object ShowLoader : StateTelevision()
    object HideLoader : StateTelevision()

    data class OnSuccess(val data: Flow<List<LiveVision>>) : StateTelevision()
    data class OnFailed(val error: String?) : StateTelevision()
    data class NoInternet(val data: Flow<List<LiveVision>>) : StateTelevision()
}
