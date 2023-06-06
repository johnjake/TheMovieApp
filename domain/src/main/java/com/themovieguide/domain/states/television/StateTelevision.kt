package com.themovieguide.domain.states.television

import com.themovieguide.domain.model.television.DetailsTelevision
import com.themovieguide.domain.model.television.LiveVision
import kotlinx.coroutines.flow.Flow

sealed class StateTelevision {
    object ShowLoader : StateTelevision()
    object HideLoader : StateTelevision()

    data class OnSuccess(val data: Flow<List<LiveVision>>) : StateTelevision()
    data class OnFailed(val error: String?) : StateTelevision()
    data class NoInternet(val data: Flow<List<LiveVision>>) : StateTelevision()
}

sealed class StateSingleTelevision {
    object ShowLoader : StateSingleTelevision()
    object HideLoader : StateSingleTelevision()

    data class OnSuccess(val data: DetailsTelevision) : StateSingleTelevision()
    data class OnFailed(val error: String?) : StateSingleTelevision()
    data class NoInternet(val data: DetailsTelevision) : StateSingleTelevision()
}

sealed class StateTelevisionSingle {
    object ShowLoader : StateTelevisionSingle()
    object HideLoader : StateTelevisionSingle()

    data class OnSuccess(val data: DetailsTelevision) : StateTelevisionSingle()
    data class OnFailed(val error: String?) : StateTelevisionSingle()
}
